package org.genguava.papyrusscroll.psi

import org.genguava.papyrusscroll.psi.resolve.RatedResolveResult

interface PapyrusImportedNameDefiner : PapyrusElement {
    fun iterateNames(): Iterable<PapyrusElement>
    fun multiResolveName(name: String): List<RatedResolveResult>
}