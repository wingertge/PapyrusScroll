package org.genguava.papyrusscroll.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiLanguageInjectionHost
import org.eclipse.jdt.internal.compiler.ast.ASTNode

interface PapyrusStringLiteralExpression : PapyrusLiteralExpression, StringLiteralExpression, PsiLanguageInjectionHost {
    val stringNodes: List<ASTNode>
    fun valueOffsetToTextOffset(valueOffset: Int): Int
    val decodedFragments: List<Pair<TextRange, String>>
    val stringValueTextRanges: List<TextRange>
}