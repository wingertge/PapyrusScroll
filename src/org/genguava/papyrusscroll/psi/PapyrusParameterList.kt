package org.genguava.papyrusscroll.psi

import com.intellij.psi.StubBasedPsiElement
import org.genguava.papyrusscroll.psi.stubs.PapyrusParameterListStub

interface PapyrusParameterList : PapyrusElement, StubBasedPsiElement<PapyrusParameterListStub> {
    val parameters: Array<PapyrusParameter>
    fun findParameterByName(name: String): PapyrusNamedParameter?
    fun addParameter(param: PapyrusNamedParameter)
    val hasPositionalContainer: Boolean
    fun getPresentableText(includeDefaultValue: Boolean, context: TypeEvalContext? = null): String
    val containingFunction: PapyrusFunction
}