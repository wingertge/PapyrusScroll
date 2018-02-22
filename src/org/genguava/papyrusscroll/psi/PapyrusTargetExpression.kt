package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.StubBasedPsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiElement
import com.intellij.psi.util.QualifiedName
import org.genguava.papyrusscroll.psi.stubs.PapyrusTargetExpressionStub


interface PapyrusTargetExpression : PapyrusQualifiedExpression, PsiNamedElement, PsiNameIdentifierOwner, PapyrusDocOwner, PapyrusQualifiedNameOwner,
                                    PapyrusReferenceOwner, StubBasedPsiElement<PapyrusTargetExpressionStub>, PapyrusPossibleClassMember {

    /**
     * Find the value that maps to this target expression in an enclosing assignment expression.
     * Does not work with other expressions (e.g. if the target is in a 'for' loop).
     *
     * Operates at the AST level.
     *
     * @return the expression assigned to target via an enclosing assignment expression, or null.
     */
    fun findAssignedValue(): PapyrusExpression?

    /**
     * Resolves the value that maps to this target expression in an enclosing assignment expression.
     *
     * This method does not access AST if underlying PSI is stub based and the context doesn't allow switching to AST.
     */
    fun resolveAssignedValue(resolveContext: PapyrusResolveContext): PsiElement?

    /**
     * Returns the qualified name (if there is any) assigned to the expression.
     *
     * This method does not access AST if underlying PSI is stub based.
     */
    fun getAssignedQName(): QualifiedName?

    /**
     * If the value assigned to the target expression is a call, returns the (unqualified and unresolved) name of the
     * callee. Otherwise, returns null.
     *
     * @return the name of the callee or null if the assigned value is not a call.
     */
    fun getCalleeName(): QualifiedName?

    override fun getReference(): PsiReference
}