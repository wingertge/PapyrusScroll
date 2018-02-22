package org.genguava.papyrusscroll.psi

import com.intellij.psi.PsiPolyVariantReference

interface PapyrusReferenceOwner : PapyrusElement {
    fun getReference(context: PapyrusResolveContext): PsiPolyVariantReference
}