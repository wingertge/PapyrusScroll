package org.genguava.papyrusscroll.psi

interface PapyrusCallSiteExpression : PapyrusExpression {
    fun getReceiver(resolvedCallee: PapyrusCallable?): PapyrusExpression?
    fun getArguments(resolvedCallee: PapyrusCallable?): List<PapyrusExpression>
}