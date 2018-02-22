package org.genguava.papyrusscroll.module

import com.intellij.platform.DirectoryProjectGenerator

abstract class PapyrusProjectGenerator<T : PapyrusNewProjectSettings> : DirectoryProjectGenerator<T> {
    companion object {
        val NO_SETTINGS = PapyrusNewProjectSettings()
    }
}