package org.genguava.papyrusscroll.psi.stubs

import com.intellij.psi.stubs.NamedStub
import org.genguava.papyrusscroll.psi.PapyrusNamedParameter

interface PapyrusNamedParameterStub : NamedStub<PapyrusNamedParameter> {
    val defaultValueText: String?
}