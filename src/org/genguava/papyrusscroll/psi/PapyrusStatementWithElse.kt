package org.genguava.papyrusscroll.psi

interface PapyrusStatementWithElse : PapyrusStatement {
    val elsePart: PapyrusElsePart?
}