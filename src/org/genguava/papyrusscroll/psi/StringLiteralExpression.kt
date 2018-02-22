package org.genguava.papyrusscroll.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

interface StringLiteralExpression : PsiElement {
    val stringValue: String
    val stringValueTextRange: TextRange
}