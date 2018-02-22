package org.genguava.papyrusscroll.psi

import com.intellij.psi.StubBasedPsiElement
import org.genguava.papyrusscroll.psi.stubs.PapyrusImportStatementStub

interface PapyrusImportStatement : PapyrusImportStatementBase, StubBasedPsiElement<PapyrusImportStatementStub>, PapyrusImplicitImportNameDefiner {
}