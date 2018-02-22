package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiFile

class TypeEvalContext private constructor(allowDataFlow: Boolean, allowStubToAST: Boolean, allowCallContext: Boolean, origin: PsiFile?) {
    class Key private constructor()

    private val constraints: TypeEvalConstraints = TypeEvalConstraints(allowDataFlow, allowStubToAST, allowCallContext, origin)

    private var trace = listOf<String>()
    private var traceIndent = ""

    private val evaluated = hashMapOf<PapyrusTypedElement, PapyrusType>()
    private val evaluatedReturn = hashMapOf<PapyrusCallable, PapyrusType>()
    private val evaluating = object: ThreadLocal<Set<PapyrusTypedElement>>() {
        override fun initialValue(): Set<PapyrusTypedElement> = hashSetOf()
    }
    private val evaluatingReturn = object: ThreadLocal<Set<PapyrusCallable>> {
        override fun initialValue(): Set<PapyrusCallable> = hashSetOf()
    }
}