package org.genguava.papyrusscroll.util

class Maybe<T> {
    val isDefined: Boolean
    private val myValue: T?

    constructor() {
        myValue = null
        isDefined = false
    }

    constructor(value: T?) {
        myValue = value
        isDefined = true
    }

    val valueOrNull get() = if(isDefined) myValue else null
    @Throws(NoSuchElementException::class)
    fun value(): T? {
        if(isDefined) return myValue!!
        throw NoSuchElementException("Accessing undefined value of Maybe")
    }

    override fun toString(): String {
        return if(isDefined) {
            if(myValue == null) "?(null)"
            else "?($myValue)"
        }
        else "?_"
    }
}