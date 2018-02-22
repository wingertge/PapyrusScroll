package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.StubBasedPsiElement
import org.genguava.papyrusscroll.psi.stubs.PapyrusNamedParameterStub

interface PapyrusNamedParameter : PapyrusParameter, PsiNamedElement, PsiNameIdentifierOwner, PapyrusExpression, StubBasedPsiElement<PapyrusNamedParameterStub> {
    val isPositionalContainer: Boolean
    fun getCanonicalRepresentation(context: TypeEvalContext? = null)
    fun getArgumentType(context: TypeEvalContext): PapyrusType?
}