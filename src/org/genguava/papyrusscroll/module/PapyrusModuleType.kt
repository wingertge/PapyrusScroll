package org.genguava.papyrusscroll.module

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.module.ModuleType
import org.jetbrains.jps.model.java.JavaSourceRootType
import org.jetbrains.jps.model.module.JpsModuleSourceRootType

abstract class PapyrusModuleType<T : ModuleBuilder>(typeId: String) : ModuleType<T>(typeId) {
    override fun isMarkInnerSupportedFor(type: JpsModuleSourceRootType<*>?) = type == JavaSourceRootType.SOURCE
}