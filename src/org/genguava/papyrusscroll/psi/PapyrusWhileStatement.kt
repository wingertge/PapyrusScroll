package org.genguava.papyrusscroll.psi

interface PapyrusWhileStatement : PapyrusLoopStatement, PapyrusStatementWithElse {
    val whilePart: PapyrusWhilePart
}