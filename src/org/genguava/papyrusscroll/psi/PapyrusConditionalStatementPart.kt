package org.genguava.papyrusscroll.psi

interface PapyrusConditionalStatementPart : PapyrusStatementPart {
    val condition: PapyrusExpression?
}