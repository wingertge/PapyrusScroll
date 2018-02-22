package org.genguava.papyrusscroll.psi

interface PapyrusParameter : PapyrusElement {
    val asNamed: PapyrusNamedParameter?
    val defaultValue: PapyrusExpression?
    val hasDefaultValue: Boolean
    val defaultValueText: String?
    val isSelf: Boolean
}