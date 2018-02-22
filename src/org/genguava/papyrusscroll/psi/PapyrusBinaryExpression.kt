package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiElement
import org.genguava.papyrusscroll.lexer.PapyrusElementType

interface PapyrusBinaryExpression : PapyrusQualifiedExpression, PapyrusCallSiteExpression, PapyrusReferenceOwner {
    val leftExpression: PapyrusExpression?
    val rightExpression: PapyrusExpression?
    val operator: PapyrusElementType?
    val psiOperator: PsiElement?
    fun isOperator(chars: String): Boolean
    @Throws(IllegalArgumentException::class)
    fun getOppositeExpression(expression: PapyrusExpression)
    fun isRightOperator(resolvedCallee: PapyrusCallable?): Boolean
}