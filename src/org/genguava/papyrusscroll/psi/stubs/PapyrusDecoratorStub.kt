package org.genguava.papyrusscroll.psi.stubs

import com.intellij.psi.stubs.StubElement
import org.genguava.papyrusscroll.psi.PapyrusDecorator
import kotlin.reflect.jvm.internal.impl.serialization.ProtoBuf

interface PapyrusDecoratorStub : StubElement<PapyrusDecorator> {
    val qualifiedName: ProtoBuf.QualifiedNameTable.QualifiedName?
}