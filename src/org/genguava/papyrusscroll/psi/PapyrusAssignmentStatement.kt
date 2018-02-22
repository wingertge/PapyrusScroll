package org.genguava.papyrusscroll.psi

interface PapyrusAssignmentStatement : PapyrusStatement, PapyrusNamedElementContainer {
    val targets: Array<PapyrusExpression>
    val rawTargets: Array<PapyrusExpression>
    val assignedValue: PapyrusExpression?
    val targetsToValuesMapping: List<Pair<PapyrusExpression, PapyrusExpression>>
    val leftHandSideExpression: PapyrusExpression?
    fun isAssignmentTo(name: String): Boolean
}