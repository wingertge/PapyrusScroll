package org.genguava.papyrusscroll.util

class Event<T> {
    private val handlers = arrayListOf<(Event<T>.(T?) -> Unit)>()
    fun plusAssign(handler: Event<T>.(T?) -> Unit) { handlers.add(handler) }
    operator fun invoke(value: T? = null) { for (handler in handlers) handler(value) }
}