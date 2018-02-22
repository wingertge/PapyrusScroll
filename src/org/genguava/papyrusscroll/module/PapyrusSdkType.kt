package org.genguava.papyrusscroll.module

import com.intellij.openapi.projectRoots.*
import org.jdom.Element

abstract class PapyrusSdkType(name: String) : SdkType(name) {
    override fun createAdditionalDataConfigurable(sdkModel: SdkModel, sdkModificator: SdkModificator): AdditionalDataConfigurable? = null
    override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {
        (additionalData as? PapyrusSdkAdditionalData)?.save(additional)
    }
}