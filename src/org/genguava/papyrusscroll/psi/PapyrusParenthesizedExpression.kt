package org.genguava.papyrusscroll.psi

interface PapyrusParenthesizedExpression : PapyrusExpression {
    val containedExpression: PapyrusExpression?
}