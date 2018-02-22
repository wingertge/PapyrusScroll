package org.genguava.papyrusscroll.psi

interface PapyrusImportStatementBase : PapyrusStatement {
    val importElements: Array<PapyrusImportElement>
    val fullyQualifiedObjectNames: List<String>
}