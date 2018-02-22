package org.genguava.papyrusscroll.module

import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.ModifiableRootModel
import org.genguava.papyrusscroll.Icons
import javax.swing.JComponent

class SKSE64Framework : ScriptExtenderFramework("Script Extender (SKSE64)") {
    override fun getIcon() = Icons.SKSE64

    override fun getPresentableName() = "Script Extender (SKSE64)"

    override fun createProvider(): FrameworkSupportInModuleProvider {
        return object: FrameworkSupportInModuleProvider() {
            override fun getFrameworkType() = this@SKSE64Framework

            override fun isEnabledForModuleType(moduleType: ModuleType<*>) = moduleType is SkyrimSEModuleType

            override fun createConfigurable(model: FrameworkSupportModel): FrameworkSupportInModuleConfigurable {
                return object: FrameworkSupportInModuleConfigurable() {
                    override fun addSupport(module: Module, rootModel: ModifiableRootModel, modifiableModelsProvider: ModifiableModelsProvider) {
                        PapyrusModulePackageService.getInstance(module)?.addPackage("SKSE64","silverlock:skse64")
                    }

                    override fun createComponent(): JComponent? {
                        return null
                    }
                }
            }
        }
    }
}