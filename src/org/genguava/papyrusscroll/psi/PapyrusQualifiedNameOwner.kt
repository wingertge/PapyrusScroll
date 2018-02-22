package org.genguava.papyrusscroll.psi

interface PapyrusQualifiedNameOwner : PapyrusElement {
    /**
     * Returns the qualified name of the element.
     *
     * @return the qualified name of the element, or null if the element doesn't have a name (for example, it is a lambda expression) or
     * is contained inside an element that doesn't have a qualified name.
     */
    fun getQualifiedName(): String?
}