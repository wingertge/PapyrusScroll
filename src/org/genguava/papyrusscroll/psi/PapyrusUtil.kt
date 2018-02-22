package org.genguava.papyrusscroll.psi

import com.intellij.injected.editor.VirtualFileWindow
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.genguava.papyrusscroll.module.PapyrusSdkType

object PapyrusUtil {
    fun VirtualFile.getPyLanguageFlavor(project: Project): LanguageFlavor {
        var virtualFile = this
        if(this is VirtualFileWindow) {
            virtualFile = this.delegate
        }

        val folder = virtualFile.parent
        if(folder != null) {
            val folderFlavor = folder.getUserData(LanguageFlavor.KEY)
            if(folderFlavor != null) return folderFlavor
            val fileFlavor = PapyrusLanguageFlavorPusher.getFileLanguageLevel(project, virtualFile)
            if(fileFlavor != null) return fileFlavor
        } else {
            val flavor = virtualFile.getUserData(LanguageFlavor.KEY)
            if(flavor != null) return flavor
        }

        return guessLanguageFlavorWithCaching(project)
    }

    fun guessLanguageFlavorWithCaching(project: Project): LanguageFlavor {
        var languageFlavor = project.getUserData(PapyrusLanguageFlavorPusher.PAPYRUS_LANGUAGE_FLAVOR)
        if(languageFlavor == null) {
            languageFlavor = guessLanguageFlavor(project)
            project.putUserData(PapyrusLanguageFlavorPusher.PAPYRUS_LANGUAGE_FLAVOR, languageFlavor)
        }

        return languageFlavor
    }

    fun guessLanguageFlavor(project: Project): LanguageFlavor {
        val moduleManager = ModuleManager.getInstance(project)
        if(moduleManager != null) {
            val flavor = moduleManager.modules
                    .map { PapyrusSdkType.findPapyrusSdk(it) }.filterNotNull()
                    .firstOrNull { PapyrusSdkType.getLanguageFlavorForSdk(it) }
            if(flavor != null) return flavor
        }
        return LanguageFlavor.SKYRIM
    }
}