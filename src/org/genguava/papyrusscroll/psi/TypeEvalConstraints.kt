package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiFile

internal class TypeEvalConstraints(val allowDataFlow: Boolean, val allowStubToAST: Boolean, val allowCallContext: Boolean, val origin: PsiFile?) {
    override fun equals(other: Any?): Boolean {
        if(this == other) return true
        if(other !is TypeEvalConstraints) return false

        if(allowDataFlow != other.allowDataFlow) return false
        if(allowStubToAST != other.allowStubToAST) return false
        if(allowCallContext != other.allowCallContext) return false
        if(origin != other.origin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = if(allowDataFlow) 1 else 0
        result = 31 * result + if(allowStubToAST) 1 else 0
        result = 31 * result + (origin?.hashCode() ?: 0)
        result = 31 * result + if(allowCallContext) 1 else 0
        return result
    }

    override fun toString() = "TypeEvalConstraints($allowDataFlow, $allowStubToAST, $allowCallContext, $origin)"
}