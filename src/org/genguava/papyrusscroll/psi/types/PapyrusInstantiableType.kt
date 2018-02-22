package org.genguava.papyrusscroll.psi.types

import org.genguava.papyrusscroll.psi.PapyrusType

interface PapyrusInstantiableType<T : PapyrusInstantiableType<T>> : PapyrusType {
    val isDefinition: Boolean
    val asInstance: T
    val asClass: T
}