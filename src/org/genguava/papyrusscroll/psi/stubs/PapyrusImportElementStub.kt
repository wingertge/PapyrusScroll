package org.genguava.papyrusscroll.psi.stubs

import com.intellij.psi.stubs.StubElement
import com.intellij.psi.util.QualifiedName
import org.genguava.papyrusscroll.psi.PapyrusImportElement

interface PapyrusImportElementStub : StubElement<PapyrusImportElement> {
    val importedQName: QualifiedName?
    val asName: String
}