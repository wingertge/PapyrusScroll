package org.genguava.papyrusscroll.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.StubBasedPsiElement
import org.genguava.papyrusscroll.codeInsight.controlflow.ScopeOwner
import org.genguava.papyrusscroll.psi.stubs.PapyrusFunctionStub

interface PapyrusFunction : PsiNamedElement, StubBasedPsiElement<PapyrusFunctionStub>, PsiNameIdentifierOwner, PapyrusStatement, PapyrusCallable,
                            PapyrusDocOwner, ScopeOwner, PapyrusDecoratable, PapyrusTypedElement, PapyrusStatementListContainer, PapyrusPossibleClassMember {

    val nameNode: ASTNode?
    fun getReturnStatementType(typeEvalContext: TypeEvalContext): PapyrusType?
    val modifier: Modifier?
    val onlyRaisesNotImplementedError: Boolean

    enum class Modifier {
        CLASS_METHOD,
        STATIC_METHOD
    }

    val property: Property?
}