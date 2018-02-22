package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.genguava.papyrusscroll.psi.resolve.RatedResolveResult
import org.genguava.papyrusscroll.psi.types.PapyrusCallableParameter
import org.genguava.papyrusscroll.psi.types.PapyrusCallableType

interface PapyrusCallExpression : PapyrusCallSiteExpression {
    override fun getReceiver(resolvedCallee: PapyrusCallable?): PapyrusExpression? {
        if(resolvedCallee is PapyrusFunction && resolvedCallee.modifier == PapyrusFunction.Modifier.STATIC_METHOD) return null
        val callee = this.callee
        return (callee as? PapyrusQualifiedExpression)?.qualifier
    }

    override fun getArguments(resolvedCallee: PapyrusCallable?) = arguments.toList()

    val callee: PapyrusExpression?
    val argumentList: PapyrusArgumentList? get() = PsiTreeUtil.getChildOfType(this, PapyrusArgumentList::class.java)
    val arguments get() = argumentList?.arguments ?: emptyArray()
    fun <T : PsiElement> getArgument(index: Int): T? {
        val args = arguments
        return if(args.size <= index) null else args[index] as? T
    }

    fun <T : PsiElement> getArgument(parameter: FunctionParameter): T? {
        val list = argumentList
        return if(list == null) null else list.getValueExpressionForParam(parameter) as? T
    }

    fun multiResolveCallee(resolveContext: PapyrusResolveContext, implicitOffset: Int = 0): List<PapyrusMarkedCallee>
    fun multiMapArguments(resolveContext: PapyrusResolveContext, implicitOffset: Int = 0): List<PapyrusArgumentsMapping>

    fun isCalleeText(vararg nameCandidates: String): Boolean {
        val callee = callee
        return callee is PapyrusReferenceExpression && nameCandidates.any { it == callee.referenceName }
    }

    class PapyrusArgumentsMapping(val callSiteExpression: PapyrusCallSiteExpression, val markedCallee: PapyrusMarkedCallee?, val implicitParameters: List<PapyrusCallableParameter>,
                                  val mappedParameters: Map<PapyrusExpression, PapyrusCallableParameter>,
                                  val unmappedParameters: List<PapyrusCallableParameter>, val unmappedArguments: List<PapyrusExpression>) {
        companion object {
            fun empty(callSiteExpression: PapyrusCallSiteExpression) = PapyrusArgumentsMapping(callSiteExpression, null, listOf(), mapOf(), listOf(), listOf())
        }
    }

    class PapyrusMarkedCallee(val callableType: PapyrusCallableType, function: PapyrusCallable?, val modifier: PapyrusFunction.Modifier?,
                              val implicitOffset: Int, val implicitlyResolved: Boolean, rate: Int) : RatedResolveResult(rate, function) {
        override fun getElement() = super.getElement() as PapyrusCallable
    }
}