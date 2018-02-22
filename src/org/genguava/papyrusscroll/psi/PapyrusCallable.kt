package org.genguava.papyrusscroll.psi

interface PapyrusCallable : PapyrusTypedElement, PapyrusQualifiedNameOwner {
    val parameterList: PapyrusParameterList
    fun getParameters(context: TypeEvalContext): List<PapyrusCallableParameter>
    fun getReturnType(context: TypeEvalContext, key: TypeEvalContext.Key): PapyrusType?
    fun getCallType(context: TypeEvalContext, callSite: PapyrusCallSiteExpression): PapyrusType?
    fun getCallType(receiver: PapyrusExpression, parameters: Map<PapyrusExpression, PapyrusCallableParameter>, context: TypeEvalContext): PapyrusType
    fun asMethod(): PapyrusFunction?
}