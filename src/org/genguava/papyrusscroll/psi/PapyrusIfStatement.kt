package org.genguava.papyrusscroll.psi

interface PapyrusIfStatement : PapyrusStatementWithElse {
    val ifPart: PapyrusIfPart
    val elifParts: Array<PapyrusIfPart>
}