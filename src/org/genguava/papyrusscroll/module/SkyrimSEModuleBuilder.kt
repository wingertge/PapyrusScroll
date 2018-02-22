package org.genguava.papyrusscroll.module

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.SdkSettingsStep
import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.projectRoots.Sdk

class SkyrimSEModuleBuilder : PapyrusModuleBuilder() {
    override fun getModuleType(): ModuleType<*> = SkyrimSEModuleType.instance

    override fun modifyProjectTypeStep(settingsStep: SettingsStep): ModuleWizardStep? {
        return object: SdkSettingsStep(settingsStep, this, { SkyrimSESdkType.instance == it }) {
            override fun onSdkSelected(sdk: Sdk?) {
                this@SkyrimSEModuleBuilder.sdk = sdk
            }
        }
    }
}