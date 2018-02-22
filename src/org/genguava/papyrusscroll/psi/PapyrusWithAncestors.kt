package org.genguava.papyrusscroll.psi

import org.genguava.papyrusscroll.psi.types.PapyrusClassLikeType

interface PapyrusWithAncestors {
    fun getAncestorTypes(context: TypeEvalContext): List<PapyrusClassLikeType>
}