package org.genguava.papyrusscroll.psi

interface PapyrusSequenceExpression : PapyrusExpression {
    val elements: Array<PapyrusElement>
    val isEmpty: Boolean
}