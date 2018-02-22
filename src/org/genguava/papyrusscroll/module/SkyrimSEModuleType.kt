package org.genguava.papyrusscroll.module

import com.intellij.openapi.module.ModuleTypeManager
import org.genguava.papyrusscroll.Icons

class SkyrimSEModuleType : PapyrusModuleType<SkyrimSEModuleBuilder>(SKYRIM_SE_MODULE) {
    companion object {
        const val SKYRIM_SE_MODULE = "SKYRIM_SE_MODULE"
        val instance get() = ModuleTypeManager.getInstance().findByID(SKYRIM_SE_MODULE)
    }

    override fun createModuleBuilder() = SkyrimSEModuleBuilder()

    override fun getName() = "Skyrim: Special Edition"

    override fun getNodeIcon(isOpened: Boolean) = Icons.SKYRIM_GLYPH

    override fun getDescription() = "Skyrim: Special Edition script module"
}