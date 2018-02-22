package org.genguava.papyrusscroll.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.util.QualifiedName

interface PapyrusQualifiedExpression : PapyrusExpression {
    val qualifier: PapyrusExpression?
    val isQualified: Boolean

    /**
     * Returns the qualified name for the expression.
     *
     *
     * If it has no qualifier, the result is the same as `QualifiedName.fromDottedString(getReferencedName())`.
     * Otherwise, the qualified name is built of `getReferencedName()` followed (to the left) by the components
     * of the qualifier *if it consists only of reference expressions*. In any other case, the result is null.
     *
     *
     * Note that it means that for pseudo-qualified operations that map to "magic" methods, the trailing component of
     * the result is the corresponding "dunder" name: `__add__`, `__neg__`, etc.
     *
     *
     * Examples:
     *
     *  * `foo -> foo`
     *  * `foo.bar -> foo.bar`
     *  * `foo[0] -> foo.__getitem__`
     *  * `foo[0].bar -> null`
     *  * `foo[0][1] -> null`
     *  * `foo().bar -> null`
     *  * `-foo -> foo.__neg__`
     *  * `foo + bar -> foo.__add__`
     *  * `foo + bar + baz -> null`
     *
     *
     * @see .getReferencedName
     */
    fun asQualifiedName(): QualifiedName?

    /**
     * Returns the name to the right of the qualifier.
     *
     * @return the name referenced by the expression.
     */
    val referenceName: String?

    /**
     * Returns the element representing the name (to the right of the qualifier).
     *
     * @return the name element.
     */
    val nameElement: ASTNode?
}