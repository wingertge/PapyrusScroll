package org.genguava.papyrusscroll.psi.stubs

import com.intellij.psi.stubs.StubOutputStream
import com.intellij.psi.util.QualifiedName

interface CustomTargetExpressionStub {
    val typeClass: Class<out CustomTargetExpressionStub>
    fun serialize(stream: StubOutputStream)
    val calleeName: QualifiedName?
}