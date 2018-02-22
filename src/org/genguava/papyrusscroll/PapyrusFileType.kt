package org.genguava.papyrusscroll

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType

class PapyrusFileType(lang: Language = PapyrusLanguage.instance) : LanguageFileType(lang) {
    companion object {
        val instance by lazy { PapyrusFileType() }
    }

    override fun getIcon() = Icons.PAPYRUS_FILE
    override fun getName() = "Papyrus"
    override fun getDefaultExtension() = "psc"
    override fun getDescription() = "Papyrus scripts"
}