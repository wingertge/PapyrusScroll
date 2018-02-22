package org.genguava.papyrusscroll.psi

import org.genguava.papyrusscroll.util.Maybe

interface Property {
    val name: String
    val setter: Maybe<PapyrusCallable>
    val getter: Maybe<PapyrusCallable>
    val doc: String?
    val definitionSite: PapyrusTargetExpression?
    fun getByDirection(direction: AccessDirection): Maybe<PapyrusCallable>
    fun getType(receiver: PapyrusExpression?, context: TypeEvalContext)
}