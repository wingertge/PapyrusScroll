package org.genguava.papyrusscroll.psi.stubs

import com.intellij.psi.stubs.NamedStub
import org.genguava.papyrusscroll.psi.PapyrusFunction

interface PapyrusFunctionStub : NamedStub<PapyrusFunction> {
    val docString: String?
    val onlyRaiseNotImplementedError: Boolean
}