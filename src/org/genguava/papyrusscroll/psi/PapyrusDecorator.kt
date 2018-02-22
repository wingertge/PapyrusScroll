package org.genguava.papyrusscroll.psi

import com.intellij.psi.StubBasedPsiElement
import com.intellij.psi.util.QualifiedName
import org.genguava.papyrusscroll.psi.stubs.PapyrusDecoratorStub

interface PapyrusDecorator : PapyrusCallExpression, StubBasedPsiElement<PapyrusDecoratorStub> {
    val target: PapyrusFunction?
    val isBuiltin: Boolean
    val hasArgumentList: Boolean
    val qualifiedName: QualifiedName?
}