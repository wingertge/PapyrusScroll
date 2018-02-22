package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiElement

interface PapyrusAugAssignmentStatement : PapyrusStatement {
    val target: PapyrusExpression
    val value: PapyrusExpression?
    val operation: PsiElement?
}