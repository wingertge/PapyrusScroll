package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiPolyVariantReference
import org.genguava.papyrusscroll.psi.resolve.QualifiedResolveResult

interface PapyrusReferenceExpression : PapyrusQualifiedExpression, PapyrusReferenceOwner {
    fun followAssignmentsChain(resolveContext: PapyrusResolveContext): QualifiedResolveResult
    fun multiFollowAssignmentsChain(resolveContext: PapyrusResolveContext): List<QualifiedResolveResult>
    val reference: PsiPolyVariantReference
}