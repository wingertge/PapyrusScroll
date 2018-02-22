package org.genguava.papyrusscroll.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.tree.IElementType
import org.genguava.papyrusscroll.psi.PapyrusClass
import org.genguava.papyrusscroll.psi.PapyrusElement
import org.genguava.papyrusscroll.psi.PapyrusFunction
import org.genguava.papyrusscroll.psi.PapyrusRecursiveElementVisitor

object PapyrusPsiUtils {
    @Suppress("UNCHECKED_CAST")
    fun <T, U : PsiElement> U.collectStubChildren(stub: StubElement<U>?, elementType: IElementType): List<T> {
        val result = mutableListOf<T>()
        if(stub != null) {
            val children = stub.childrenStubs
            result.addAll(children.filter { it.stubType == elementType }.map { it.psi as T })
        } else {
            this.acceptChildren(object: TopLevelVisitor() {
                override fun checkAddElement(node: PsiElement) {
                    if(node.node.elementType == elementType)
                        result.add(node as T)
                }
            })
        }
        return result
    }

    fun PsiElement.collectAllStubChildren(stub: StubElement<*>?): List<PsiElement> {
        val result = mutableListOf<PsiElement>()
        if(stub != null) {
            val children = stub.childrenStubs
            result.addAll(children.map { it.psi })
        } else {
            this.acceptChildren(object: TopLevelVisitor() {
                override fun checkAddElement(node: PsiElement) {
                    result.add(node)
                }
            })
        }
        return result
    }

    private abstract class TopLevelVisitor : PapyrusRecursiveElementVisitor() {
        override fun visitPapElement(node: PapyrusElement) {
            super.visitPapElement(node)
            checkAddElement(node)
        }

        override fun visitPapClass(node: PapyrusClass) = checkAddElement(node)
        override fun visitPapFunction(node: PapyrusFunction) = checkAddElement(node)
        abstract fun checkAddElement(node: PsiElement)
    }
}