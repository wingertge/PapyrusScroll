package org.genguava.papyrusscroll.psi

import org.codehaus.groovy.ast.ASTNode

interface PapyrusArgumentList : PapyrusElement {
    val argumentExpressions: Collection<PapyrusExpression>
    val arguments: Array<PapyrusExpression>
    fun addArgument(arg: PapyrusExpression)
    fun addArgumentFirst(arg: PapyrusExpression)
    fun addArgumentAfter(argument: PapyrusExpression, afterThis: PapyrusExpression)
    val callExpression: PapyrusCallExpression?
    val closingTag: ASTNode?
    fun getValueExpressionForParam(parameter: FunctionParameter): PapyrusExpression?
}