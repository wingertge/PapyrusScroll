package org.genguava.papyrusscroll

import com.intellij.lang.Language

class PapyrusLanguage : Language("Papyrus") {
    companion object {
        val instance by lazy { PapyrusLanguage() }
    }

    override fun isCaseSensitive() = true
}