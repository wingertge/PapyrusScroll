package org.genguava.papyrusscroll.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.StubBasedPsiElement
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.util.ArrayFactory
import com.intellij.util.Processor
import org.genguava.papyrusscroll.codeInsight.controlflow.ScopeOwner
import org.genguava.papyrusscroll.psi.stubs.PapyrusClassStub
import org.genguava.papyrusscroll.psi.types.PapyrusClassLikeType

interface PapyrusClass : PsiNameIdentifierOwner, PapyrusStatement, PapyrusDocOwner, StubBasedPsiElement<PapyrusClassStub>,
        ScopeOwner, PapyrusDecoratable, PapyrusTypedElement, PapyrusStatementListContainer, PapyrusWithAncestors {
    companion object {
        val EMPTY_ARRAY = arrayOf<PapyrusClass>()
        val ARRAY_FACTORY: ArrayFactory<PapyrusClass?> = ArrayFactory { if(it == 0) EMPTY_ARRAY else arrayOfNulls<PapyrusClass?>(it) }
    }

    val nameNode: ASTNode?
    fun getAncestorClasses(context: TypeEvalContext?): List<PapyrusClass>
    fun getSuperClassTypes(context: TypeEvalContext): List<PapyrusClassLikeType>
    fun getSuperClasses(context: TypeEvalContext?): Array<PapyrusClass>
    fun getSuperClassExpressionList(): PapyrusArgumentList?
    fun getSuperClassExpressions(): Array<PapyrusExpression>
    fun getMethods(): Array<PapyrusFunction>
    fun getEvents(): Array<PapyrusEvent>
    fun getProperties(): Map<String, Property>
    fun findMethodByName(name: String?, inherited: Boolean, context: TypeEvalContext?): PapyrusFunction?
    fun multiFindMethodByName(name: String, inherited: Boolean, context: TypeEvalContext?): List<PapyrusFunction>
    fun findProperty(name: String, inherited: Boolean, context: TypeEvalContext?): Property
    fun visitMethods(processor: Processor<PapyrusFunction>, inherited: Boolean, context: TypeEvalContext?): Boolean
    val nestedClasses: Array<PapyrusClass>
    fun scanProperties(processor: Processor<Property>, inherited: Boolean): Property
    fun findPropertyByCallable(callable: PapyrusCallable): Property
    fun isSubClass(parent: PapyrusClass, context: TypeEvalContext?): Boolean
    fun isSubClass(superClassQName: String, context: TypeEvalContext?): Boolean
    fun processClassLevelDeclarations(processor: PsiScopeProcessor): Boolean
    fun processInstanceLevelDeclarations(processor: PsiScopeProcessor, location: PsiElement?)
    fun getType(context: TypeEvalContext?): PapyrusClassLikeType?
}