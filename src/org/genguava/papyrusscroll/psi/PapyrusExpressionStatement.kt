package org.genguava.papyrusscroll.psi

interface PapyrusExpressionStatement : PapyrusStatement {
    val expression: PapyrusExpression
}