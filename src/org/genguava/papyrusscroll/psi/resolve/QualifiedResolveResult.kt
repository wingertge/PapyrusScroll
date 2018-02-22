package org.genguava.papyrusscroll.psi.resolve

import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveResult
import org.genguava.papyrusscroll.psi.PapyrusExpression

interface QualifiedResolveResult : ResolveResult {
    companion object {
        val EMPTY = object: QualifiedResolveResult {
            override val qualifiers = listOf<PapyrusExpression>()
            override val implicit = false
            override fun getElement() = null
            override fun isValidResult() = false
        }
    }

    val qualifiers: List<PapyrusExpression>
    val implicit: Boolean
}