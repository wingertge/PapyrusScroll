package org.genguava.papyrusscroll.psi.resolve

import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveResult

open class RatedResolveResult(val rate: Int, private val myElement: PsiElement?) : ResolveResult {
    override fun isValidResult() = myElement != null
    fun replace(element: PsiElement?) = RatedResolveResult(rate, element)
    override fun toString() = "$element@$rate"
    override fun getElement() = myElement

    companion object {
        const val RATE_HIGH = 1000
        const val RATE_NORMAL = 0
        const val RATE_LOW = -1000

        fun List<RatedResolveResult>.sorted() = this.sortedByDescending { it.rate }
    }
}