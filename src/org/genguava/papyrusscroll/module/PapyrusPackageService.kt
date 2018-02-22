package org.genguava.papyrusscroll.module

import com.intellij.openapi.components.*
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleServiceManager
import com.intellij.openapi.project.Project
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "PapyrusPackageService", storages = [Storage(value = "packages.xml", roamingType = RoamingType.DISABLED)])
class PapyrusPackageService : PersistentStateComponent<PapyrusPackageService> {
    companion object {
        private const val PSPI_REPO_URL = "https://repo.papyrusscroll.com"
        const val PSPI_SKYRIM_SE_URL = PSPI_REPO_URL + "/skyrim_se"

        val instance = ServiceManager.getService(PapyrusPackageService::class.java)
    }

    @Volatile var repositories = ContainerUtil.createConcurrentList(mutableListOf(SkyrimSERepository("PapyrusScroll Package Index", PSPI_SKYRIM_SE_URL, true)))
    @Volatile private var packageCache = ContainerUtil.createConcurrentList<PapyrusPackage>()

    override fun getState() = this

    override fun loadState(state: PapyrusPackageService) {
        XmlSerializerUtil.copyBean(state, this)
    }

    fun getPackageList(forceRefresh: Boolean = false): List<PapyrusPackage> {
        if(!forceRefresh && packageCache.isNotEmpty()) return packageCache.toList()

        synchronized(packageCache) {
            packageCache.clear()
            packageCache.addAll(fetchPackages())
            return packageCache.toList()
        }
    }

    private fun fetchPackages(): List<PapyrusPackage> {
        val result = mutableMapOf<String, PapyrusPackage>()
        repositories
                .filter { it.supportsListOp }
                .flatMap { it.listPackages() }
                .forEach { pPackage ->
                    if(result.containsKey(pPackage.name)) {
                        val current = result[pPackage.name]!!
                        current.versions.addAll(pPackage.versions)
                        result[pPackage.name] = current
                    } else result[pPackage.name] = pPackage
                }
        return result.values.toList()
    }
}