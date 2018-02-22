package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiElement

open class PapyrusRecursiveElementVisitor : PapyrusElementVisitor() {
    override fun visitElement(element: PsiElement) {
        element.acceptChildren(this)
    }
}