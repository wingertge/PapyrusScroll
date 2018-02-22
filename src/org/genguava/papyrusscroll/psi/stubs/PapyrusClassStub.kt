package org.genguava.papyrusscroll.psi.stubs

import com.intellij.psi.stubs.NamedStub
import com.intellij.psi.util.QualifiedName
import org.genguava.papyrusscroll.psi.PapyrusClass

interface PapyrusClassStub : NamedStub<PapyrusClass> {
    val superClasses: Map<QualifiedName, QualifiedName>
    val metaClass: QualifiedName?
    val slots: List<String>?
    val docString: String?
}