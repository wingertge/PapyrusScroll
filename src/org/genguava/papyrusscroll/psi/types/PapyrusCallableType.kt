package org.genguava.papyrusscroll.psi.types

import org.genguava.papyrusscroll.psi.PapyrusCallSiteExpression
import org.genguava.papyrusscroll.psi.PapyrusType
import org.genguava.papyrusscroll.psi.TypeEvalContext

interface PapyrusCallableType : PapyrusType {
    val isCallable get() = true
    fun getReturnType(context: TypeEvalContext): PapyrusType?
    fun getCallType(context: TypeEvalContext, callSite: PapyrusCallSiteExpression): PapyrusType?
    fun getParameters(context: TypeEvalContext): List<PapyrusCallableParameter>? = null
}