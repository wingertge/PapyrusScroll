package org.genguava.papyrusscroll.psi

class PapyrusResolveContext(val allowImplicits: Boolean, val allowProperties: Boolean, typeEvalContext: TypeEvalContext? = null) {
    val typeEvalContext = typeEvalContext
        get() = field ?: TypeEvalContext.codeInsightFallback(null)

    companion object {
        val defaultContext = PapyrusResolveContext(true, true)
        val noImplicits = PapyrusResolveContext(false, true)
        val noProperties = PapyrusResolveContext(false, false)
    }

    fun withTypeEvalContext(context: TypeEvalContext) = PapyrusResolveContext(allowImplicits, allowProperties, context)
    fun withoutImplicits() = PapyrusResolveContext(false, allowProperties, typeEvalContext)

    override fun equals(other: Any?): Boolean {
        if(this == other) return true
        if(other !is PapyrusResolveContext) return false

        if(allowImplicits != other.allowImplicits) return false
        if(typeEvalContext != other.typeEvalContext) return false
        return true
    }

    override fun hashCode(): Int {
        var result = if(allowImplicits) 1 else 0
        result = 31 * result + (typeEvalContext?.hashCode() ?: 0)
        return result
    }
}