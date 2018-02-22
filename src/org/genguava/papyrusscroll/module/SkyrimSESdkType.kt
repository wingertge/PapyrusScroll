package org.genguava.papyrusscroll.module

import com.intellij.openapi.projectRoots.SdkType
import org.genguava.papyrusscroll.Icons
import org.genguava.papyrusscroll.util.RegistryUtils
import java.io.File

class SkyrimSESdkType : PapyrusSdkType("Skyrim SE Game Install") {
    companion object {
        val instance get() = SdkType.findInstance(SkyrimSESdkType::class.java)
    }

    override fun getPresentableName() = name

    override fun isValidSdkHome(path: String?) = path != null && File(path, "SkyrimSE.exe").exists()
    override fun suggestSdkName(currentSdkName: String?, sdkHome: String?) = "Skyrim SE"

    override fun suggestHomePath() = RegistryUtils.findApplicationInstallLocation("The Elder Scrolls V: Skyrim Special Edition")

    override fun getIconForAddAction() = Icons.SKYRIM_SE_BIN
    override fun getIcon() = Icons.SKYRIM_GLYPH
}