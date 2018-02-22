package org.genguava.papyrusscroll.psi

import com.intellij.openapi.util.Key

enum class LanguageFlavor {
    SKYRIM,
    SKYRIM_SE,
    FALLOUT4;

    companion object {
        val KEY = Key<LanguageFlavor>("papyrus.language.flavor")
    }
}