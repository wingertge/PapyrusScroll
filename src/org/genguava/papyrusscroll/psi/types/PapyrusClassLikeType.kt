package org.genguava.papyrusscroll.psi.types

import com.intellij.psi.PsiElement
import com.intellij.util.Processor
import org.genguava.papyrusscroll.psi.*

interface PapyrusClassLikeType : PapyrusCallableType, PapyrusWithAncestors, PapyrusInstantiableType<PapyrusClassLikeType> {
    val classQName: String?
    fun getSuperClassTypes(context: TypeEvalContext): List<PapyrusClassLikeType>
    fun resolveMember(name: String, location: PapyrusExpression?, direction: AccessDirection, resolveContext: PapyrusResolveContext, inherited: Boolean)
    fun visitMembers(processor: Processor<PsiElement>, inherited: Boolean, context: TypeEvalContext)
    fun getMemberNames(inherited: Boolean, context: TypeEvalContext): Set<String>
    val valid: Boolean
    fun getMetaClassType(context: TypeEvalContext, inherited: Boolean): PapyrusClassLikeType?
}