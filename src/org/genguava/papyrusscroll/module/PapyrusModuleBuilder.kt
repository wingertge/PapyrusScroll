package org.genguava.papyrusscroll.module

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.module.ModifiableModuleModel
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.platform.DirectoryProjectGenerator
import org.genguava.papyrusscroll.util.Event


abstract class PapyrusModuleBuilder(private val generator: DirectoryProjectGenerator<in PapyrusNewProjectSettings>? = null) : ModuleBuilder() {
    val onSdkChanged = Event<Any>()
    var sdk: Sdk? = null
        set(value) {
            if(field != value) {
                field = value
                onSdkChanged()
            }
        }

    override fun getGroupName() = "Papyrus"

    override fun setupRootModel(rootModel: ModifiableRootModel) {
        if(myJdk !=  null)
            rootModel.sdk = myJdk
        else rootModel.inheritSdk()

        doAddContentEntry(rootModel)
    }

    override fun commitModule(project: Project, model: ModifiableModuleModel?): Module? {
        val module = super.commitModule(project, model)
        if (module != null && generator != null) {
            val moduleRootManager = ModuleRootManager.getInstance(module)
            val contentRoots = moduleRootManager.contentRoots
            var dir = module.project.baseDir
            if (contentRoots.isNotEmpty() && contentRoots[0] != null) {
                dir = contentRoots[0]
            }
            generator.generateProject(project, dir, PapyrusNewProjectSettings(), module) //replace with appropriate no settings
        }
        return module
    }
}