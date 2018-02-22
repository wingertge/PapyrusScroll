package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.StubBasedPsiElement
import com.intellij.psi.util.QualifiedName
import org.genguava.papyrusscroll.psi.resolve.RatedResolveResult
import org.genguava.papyrusscroll.psi.stubs.PapyrusImportElementStub

interface PapyrusImportElement : PapyrusElement, PapyrusImportedNameDefiner, StubBasedPsiElement<PapyrusImportElementStub> {
    val importReferenceExpression: PapyrusReferenceExpression?
    val importedQName: QualifiedName?
    val asNameElement: PapyrusTargetExpression?
    val asName: String?
    val visibleName: String?
    val containingImportStatement: PapyrusStatement?
    fun getElementNamed(name: String, resolveImportElement: Boolean): PsiElement?
    fun multiResolve(): List<RatedResolveResult>
}