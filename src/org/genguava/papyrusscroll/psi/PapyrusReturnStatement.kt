package org.genguava.papyrusscroll.psi

interface PapyrusReturnStatement : PapyrusStatement {
    val expression: PapyrusExpression?
}