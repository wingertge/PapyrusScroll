package org.genguava.papyrusscroll.lexer

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import org.genguava.papyrusscroll.PapyrusFileType
import java.lang.reflect.Constructor

class PapyrusElementType(debugName: String, protected var psiElementClass: Class<out PsiElement>? = null) : IElementType(debugName, PapyrusFileType.instance.language) {
    companion object {
        val PARAMETER_TYPES = arrayOf(ASTNode::class.java)
    }

    private var constructor: Constructor<out PsiElement>? = null

    fun createElement(node: ASTNode): PsiElement {
        if(psiElementClass == null)
            throw IllegalStateException("Cannot create an element for ${node.elementType} without element class")
        try {
            if(constructor == null)
                constructor = psiElementClass!!.getConstructor(*PARAMETER_TYPES)

            return constructor!!.newInstance(node)
        } catch (e: Exception) {
            throw IllegalStateException("No necessary constructor for ${node.elementType}", e)
        }
    }

    override fun toString(): String {
        return "Beth:${super.toString()}"
    }

    constructor(debugName: String) : this(debugName, null)
}