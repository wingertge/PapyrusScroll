package org.genguava.papyrusscroll.module

import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleServiceManager
import com.intellij.util.containers.ContainerUtil

class PapyrusModulePackageService {
    companion object {
        fun getInstance(module: Module) = ModuleServiceManager.getService(module, PapyrusModulePackageService::class.java)
    }

    @Volatile private var dependencies = ContainerUtil.createConcurrentList<PapyrusDependency>()

    fun addPackage(name: String, path: String, version: String? = null) {
        val dependency = if(version != null) PapyrusDependency(name, path, PapyrusPackageVersion(version)) else PapyrusDependency(name, path)
        dependencies.add(dependency)
    }
}