package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.genguava.papyrusscroll.codeInsight.controlflow.ScopeOwner
import org.genguava.papyrusscroll.psi.resolve.RatedResolveResult

interface PapyrusFile : PapyrusElement, PsiFile, PapyrusDocOwner, ScopeOwner {
    val topLevelClass: PapyrusClass?
    val languageFlavor: LanguageFlavor
    fun findExportedName(name: String): PsiElement?
    fun iterateNames(): Iterable<PapyrusElement>
    fun multiResolveName(name: String): List<RatedResolveResult>
    fun multiResolveName(name: String, exported: Boolean): List<RatedResolveResult>
    val importTargets: List<PapyrusImportElement>
    val importBlock: List<PapyrusImportStatementBase>
}