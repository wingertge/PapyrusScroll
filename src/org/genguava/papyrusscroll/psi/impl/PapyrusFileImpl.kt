package org.genguava.papyrusscroll.psi.impl

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.lang.Language
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.RecursionManager
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.psi.*
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.indexing.IndexingDataKeys
import org.genguava.papyrusscroll.Icons
import org.genguava.papyrusscroll.PapyrusFileType
import org.genguava.papyrusscroll.PapyrusLanguage
import org.genguava.papyrusscroll.psi.*
import org.genguava.papyrusscroll.psi.resolve.RatedResolveResult
import java.io.File
import java.lang.ref.SoftReference
import org.genguava.papyrusscroll.psi.impl.PapyrusPsiUtils.collectAllStubChildren
import org.genguava.papyrusscroll.psi.impl.PapyrusPsiUtils.collectStubChildren

class PapyrusFileImpl(viewProvider: FileViewProvider, language: Language = PapyrusLanguage.instance) : PsiFileBase(viewProvider, language), PapyrusFile, PapyrusExpression {
    private val modificationTracker: PsiModificationTracker = PsiModificationTracker.SERVICE.getInstance(project)
    private val PROCESSED_FILES = Key.create<MutableSet<PapyrusFile>>("PapyrusFileImpl.processDeclarations.processedFiles")
    private @Volatile var myExportedNameCache = SoftReference<ExportedNameCache>(null)

    override fun getFileType() = PapyrusFileType.instance
    override fun toString() = "PapyrusFile:$name"

    override val languageFlavor: LanguageFlavor get() {
        if(myOriginalFile != null) return (originalFile as PapyrusFile).languageFlavor
        var vFile = virtualFile
        if(vFile == null)
            vFile = getUserData(IndexingDataKeys.VIRTUAL_FILE)
        if(vFile == null)
            vFile = viewProvider.virtualFile
        return vFile.getPapyrusLanguageFlavor(project) // PyUtil parallel
    }

    override fun getIcon(flags: Int) = Icons.PAPYRUS_FILE

    override fun accept(visitor: PsiElementVisitor) {
        if(isAcceptedFor(visitor.javaClass)) {
            if(visitor is PapyrusElementVisitor) visitor.visitPapyrusFile(this)
            else super.accept(visitor)
        }
    }

    fun isAcceptedFor(visitorClass: Class<*>): Boolean {
        viewProvider.languages.forEach {
            val filters = PapyrusVisitorFilter.instance.allForLanguage(it)
            filters.forEach {
                if(!it.isSupported(visitorClass, this)) return false
            }
        }
        return true
    }

    override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
        var resolveState = state
        var papyrusFiles = resolveState.get(PROCESSED_FILES)
        if(papyrusFiles == null) {
            papyrusFiles = mutableSetOf()
            resolveState = resolveState.put(PROCESSED_FILES, papyrusFiles)
        }
        if(papyrusFiles.contains(this)) return true
        papyrusFiles.add(this)

        if(topLevelClass != lastParent && topLevelClass != null && !processor.execute(topLevelClass, resolveState)) return false
        return true
    }

    override fun findExportedName(name: String): PsiElement? {
        val results = multiResolveName(name)
        val elements = mutableListOf<PsiElement>()
        results.forEach {
            val element = it.element
            val importedResult = it as? ImportedResolveResult
            if(importedResult != null) {
                val definer = importedResult.definer
                if(definer != null) elements.add(definer)
            } else if(element != null && element.containingFile == this) elements.add(element)
        }
        val element = if(elements.isEmpty()) null else elements[elements.size - 1]
        if(element != null && !element.isValid) throw PsiInvalidElementAccessException(element)
        return element
    }

    override fun multiResolveName(name: String) = multiResolveName(name, true)

    override fun multiResolveName(name: String, exported: Boolean): List<RatedResolveResult> {
        val results = RecursionManager.doPreventingRecursion(this, false, { exportedNameCache.multiResolve(name) })
        if(results != null && !results.isEmpty()) return results
        return listOf()
    }

    private val exportedNameCache: ExportedNameCache get() {
        var cache = myExportedNameCache.get()
        val modificationStamp = modificationStamp
        if(cache != null && modificationStamp != cache.modificationStamp) {
            myExportedNameCache.clear()
            cache = null
        }
        if(cache == null) {
            cache = ExportedNameCache(modificationStamp)
            myExportedNameCache = SoftReference(cache)
        }
        return cache
    }

    override fun iterateNames(): Iterable<PapyrusElement> {
        val result = mutableListOf<PapyrusElement>()
        val processor = object: VariantsProcessor(this) {
            override fun addElement(name: String, element: PsiElement) {
                val nElement = PapyrusUtil.turnDirIntoInit(element)
                if(nElement is PapyrusElement)
                    result.add(nElement)
            }
        }
        processDeclarations(processor, ResolveState.initial(), null, this)
        return result
    }

    override val importTargets: List<PapyrusImportElement> get() {
        val ret = mutableListOf<PapyrusImportElement>()
        val imports = this.collectStubChildren(stub, PapyrusElementTypes.IMPORT_STATEMENT)
        imports.forEach {
            ret.addAll(it.importElements)
        }
        return ret
    }

    override fun getType(context: TypeEvalContext, key: TypeEvalContext.Key) = topLevelClass?.getType(context, key)

    override fun clearCaches() {
        super<PapyrusFile>.clearCaches()
        ControlFlowCache.clear(this)
        myExportedNameCache.clear()
    }

    override fun delete() {
        val path = virtualFile.path
        super.delete()
        PapyrusUtil.deletePscFiles(path)
    }

    override fun setName(name: String): PsiElement {
        val path = virtualFile.path
        val newElement = super.setName(name)
        PayrusUtil.deletePscFiles(path)
        return newElement
    }

    override val importBlock: List<PapyrusImportStatementBase> get() {
        val result = mutableListOf<PapyrusImportStatementBase>()
        val firstChild = firstChild
        val firstImport = firstChild as? PapyrusImportStatementBase
                ?: PsiTreeUtil.getNextSiblingOfType(firstChild, PapyrusImportStatementBase::class.java)
        if(firstImport != null) {
            result.add(firstImport)
            var nextImport = PapyrusPsiUtils.getNextNonCommentSibling(firstImport, true)
            while (nextImport is PapyrusImportStatementBase) {
                result.add(nextImport)
                nextImport = PapyrusPsiUtils.getNextNonCommentSibling(nextImport, true)
            }
        }
        return result
    }

    companion object {
        fun getStringListFromTargetExpression(name: String, attrs: List<PapyrusTargetExpression>): List<String>? {
            attrs.forEach {
                if(name == it.name) return PapyrusUtil.getStringListFromTargetExpression(it)
            }
            return null
        }
    }

    override fun getPresentation(): ItemPresentation? {
        return object: ItemPresentation {
            override fun getPresentableText() = getScriptName(this@PapyrusFileImpl)
            private fun getScriptName(file: PapyrusFile) = FileUtil.getNameWithoutExtension(file.name)
            override fun getIcon(unused: Boolean) = Icons.PAPYRUS_FILE

            override fun getLocationString(): String? {
                val name = locationName
                return if(name != null) "($name)" else null
            }

            private val locationName: String? get() {
                val name = QualifiedNameFinder.findShortestImportableQName(this@PapyrusFileImpl)
                if(name != null) {
                    val prefix = name.removeTail(1)
                    if(prefix.componentCount > 0)
                        return prefix.toString()
                }
                val relativePath = relativeContainerPath
                if(relativePath != null) return relativePath
                val psiDirectory = parent
                if(psiDirectory != null)
                    return psiDirectory.virtualFile.presentableUrl
                return null
            }

            private val relativeContainerPath: String? get() {
                val psiDirectory = parent
                if(psiDirectory != null) {
                    val vFile = virtualFile
                    if(vFile != null) {
                        val root = ProjectFileIndex.SERVICE.getInstance(project).getContentRootForFile(vFile)
                        if(root != null) {
                            val parent = vFile.parent
                            val rootParent = root.parent
                            if(rootParent != null && parent != null)
                                return VfsUtilCore.getRelativePath(parent, rootParent, File.separatorChar)
                        }
                    }
                }
                return null
            }
        }
    }

    private class ArrayListThreadLocal : ThreadLocal<List<String>>() {
        override fun initialValue(): List<String> = arrayListOf()
    }

    inner class ExportedNameCache(val modificationStamp: Long) {
        private val namedElements = mutableMapOf<String, MutableList<PsiNamedElement>>()
        private val importedNameDefiners = mutableListOf<PapyrusImportedNameDefiner>()
        private val nameDefinerNegativeCache = mutableListOf<String>()
        private var nameDefinerOOCBModCount: Long = -1

        init {
            processDeclarations(this@PapyrusFileImpl.collectAllStubChildren(stub), {
                if(it is PsiNamedElement) {
                    val name = it.name!!
                    if(!namedElements.containsKey(name))
                        namedElements[name] = arrayListOf()
                    val elements = namedElements[name]!!.toMutableList()
                    elements.add(it)
                }
                if(it is PapyrusImportStatement)
                    importedNameDefiners.addAll(it.importElements)
                return@processDeclarations true
            })

            namedElements.values.forEach { it.reverse() }
            importedNameDefiners.reverse()
        }

        private fun processDeclarations(elements: List<PsiElement>, processor: (PsiElement) -> Boolean): Boolean {
            elements.forEach {
                if(!processor(it)) return false
                if(it is PapyrusExceptPart && !processDeclarations(part.collectAllStubChildren(part.stub), processor))
                    return false
            }
            return true
        }

        fun multiResolve(name: String): List<RatedResolveResult> {
            synchronized(nameDefinerNegativeCache) {
                val modCount = modificationTracker.outOfCodeBlockModificationCount
                if(modCount != nameDefinerOOCBModCount) {
                    nameDefinerNegativeCache.clear()
                    nameDefinerOOCBModCount = modCount
                } else {
                    if(nameDefinerNegativeCache.contains(name)) return listOf()
                }
            }

            val processor = PapyrusResolveProcessor(name)
            var stopped = false
            if(namedElements.containsKey(name)) {
                for(element in namedElements[name]!!) {
                    if(!processor(element, ResolveState.initial())) {
                        stopped = true
                        break
                    }
                }
            }
            if(!stopped) {
                for(definer in importedNameDefiners) {
                    if(!processor(definer, ResolveState.initial())) break
                }
            }
            val results: Map<PsiElement?, PapyrusImportedNameDefiner?> = processor.results
            if(!results.isEmpty()) {
                val resultList = ResolveResultList()
                val typeEvalContext = TypeEvalContext.codeInsightFallback(project)
                results.entries.forEach {
                    val element = it.key
                    val definer = it.value
                    if(element != null) {
                        val elementRate = element.getRate(typeEvalContext)
                        if(definer != null) resultList.add(ImportedResolveResult(element, elementRate, definer))
                        else resultList.poke(element, elementRate)
                    }
                }

                return resultList
            }

            synchronized(nameDefinerNegativeCache) {
                nameDefinerNegativeCache.add(name)
            }

            return listOf()
        }
    }
}