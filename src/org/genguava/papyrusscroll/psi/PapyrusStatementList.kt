package org.genguava.papyrusscroll.psi

interface PapyrusStatementList : PapyrusElement {
    val statements: Array<PapyrusStatement>
}