package org.genguava.papyrusscroll.psi

import com.intellij.psi.StubBasedPsiElement
import org.genguava.papyrusscroll.psi.stubs.PapyrusDecoratorListStub

interface PapyrusDecoratorList : PapyrusElement, StubBasedPsiElement<PapyrusDecoratorListStub> {
    val decorators: Array<PapyrusDecorator>
    fun findDecorator(name: String): PapyrusDecorator?
}