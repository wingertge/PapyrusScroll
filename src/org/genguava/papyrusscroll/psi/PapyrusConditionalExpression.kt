package org.genguava.papyrusscroll.psi

interface PapyrusConditionalExpression : PapyrusExpression {
    val truePart: PapyrusExpression
    val condition: PapyrusExpression?
    val falsePart: PapyrusExpression?
}