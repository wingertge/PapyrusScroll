package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement

interface PapyrusNamedElementContainer : PsiElement {
    val namedElements: List<PsiNamedElement>
}