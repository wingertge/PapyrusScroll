package org.genguava.papyrusscroll.psi

import com.intellij.openapi.util.Key
import com.intellij.util.ProcessingContext
import com.intellij.psi.PsiElement
import org.genguava.papyrusscroll.psi.resolve.RatedResolveResult


interface PapyrusType {

    /**
     * Resolves an attribute of type.
     *
     * @param name      attribute name
     * @param location  the expression of type qualifierType on which the member is being resolved (optional)
     * @param direction
     * @param resolveContext
     * @return null if name definitely cannot be found (e.g. in a qualified reference),
     * or an empty list if name is not found but other contexts are worth looking at,
     * or a list of elements that define the name, a la multiResolve().
     */
    fun resolveMember(name: String, location: PapyrusExpression?, direction: AccessDirection, resolveContext: PapyrusResolveContext): List<RatedResolveResult>?

    /**
     * Proposes completion variants from type's attributes.
     *
     *
     * @param location   the reference on which the completion was invoked
     * @param context    to share state between nested invocations
     * @return completion variants good for [com.intellij.psi.PsiReference.getVariants] return value.
     */
    fun getCompletionVariants(completionPrefix: String, location: PsiElement, context: ProcessingContext): Array<Any>

    /**
     * Context key for access to a set of names already found by variant search.
     */
    val CTX_NAMES: Key<Set<String>> get() = Key("Completion variants names")

    /**
     * TODO rename it to something like getPresentableName(), because it's not clear that these names are actually visible to end-user
     * @return name of the type
     */
    fun getName(): String?

    /**
     * @return true if the type is a known built-in type.
     */
    fun isBuiltin(): Boolean
    fun assertValid(message: String)
}