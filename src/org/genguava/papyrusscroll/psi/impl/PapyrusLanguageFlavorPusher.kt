package org.genguava.papyrusscroll.psi.impl

import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.roots.impl.FilePropertyPusher
import com.intellij.openapi.roots.impl.PushedFilePropertiesUpdater
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.messages.MessageBus
import org.genguava.papyrusscroll.module.PapyrusSdkType
import org.genguava.papyrusscroll.psi.LanguageFlavor
import org.genguava.papyrusscroll.psi.PapyrusUtil

class PapyrusLanguageFlavorPusher : FilePropertyPusher<LanguageFlavor> {
    companion object {
        val PAPYRUS_LANGUAGE_FLAVOR = Key.create<LanguageFlavor>("PAPYRUS_LANGUAGE_LEVEL")

        fun pushLanguageFlavor(project: Project) {
            PushedFilePropertiesUpdater.getInstance(project).pushAll(PapyrusLanguageFlavorPusher())
        }
    }

    private val moduleSdks = ContainerUtil.createWeakMap<Module, Sdk>()

    override fun initExtra(project: Project, bus: MessageBus, languageFlavorUpdater: FilePropertyPusher.Engine) {
        val moduleSdks : Map<Module, Sdk> = getPapyrusModuleSdks(project)
        val distinctSdks = moduleSdks.values.filterNotNull().toHashSet()

        this.moduleSdks.putAll(moduleSdks)
        PapyrusUtil.invalidateLanguageFlavorCache(project, distinctSdks)
        updateSdkLanguageFlavors(project, distinctSdks)
        project.putUserData(PAPYRUS_LANGUAGE_FLAVOR, PapyrusUtil.guessLanguageFlavor(project))
    }

    override fun getFileDataKey() = LanguageFlavor.KEY
    override fun getDefaultValue() = LanguageFlavor.SKYRIM
    override fun pushDirectoriesOnly() = true

    fun getFileLanguageLevel(project: Project, file: VirtualFile?): LanguageFlavor? {
        if(file == null) return null
        val sdk = getFileSdk(project, file)
        if(sdk != null) return PapyrusSdkType.getLanguageFlavorForSdk(sdk)
        return PapyrusUtil.guessLanguageFlavorWithCaching(project)
    }

    private fun getFileSdk(project: Project, file: VirtualFile): Sdk? {
        val module = ModuleUtilCore.findModuleForFile(file, project)
        return if(module != null) {
            PapyrusSdkType.findPapyrusSdk(module)
        } else findSdkForFileOutsideTheProject(project, file)
    }

    private fun findSdkForFileOutsideTheProject(project: Project, file: VirtualFile): Sdk? {

    }
}