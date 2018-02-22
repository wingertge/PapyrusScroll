package org.genguava.papyrusscroll.psi

interface PapyrusTypedElement : PapyrusElement {
    fun getType(context: TypeEvalContext, key: TypeEvalContext.Key): PapyrusType?
}