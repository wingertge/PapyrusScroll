package org.genguava.papyrusscroll.lexer

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.IndexSink
import com.intellij.psi.stubs.StubElement
import org.genguava.papyrusscroll.PapyrusFileType
import org.genguava.papyrusscroll.psi.PapyrusElement

abstract class PapyrusStubElementType<StubT : StubElement<*>, PsiT : PapyrusElement>(debugName: String) : IStubElementType<StubT, PsiT>(debugName, PapyrusFileType.instance.language) {
    abstract fun createElement(node: ASTNode): PsiElement
    override fun indexStub(stub: StubT, sink: IndexSink) { }
    override fun getExternalId() = "beth.${super.toString()}"
}