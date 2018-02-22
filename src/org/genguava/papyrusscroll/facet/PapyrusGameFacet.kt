package org.genguava.papyrusscroll.facet

import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.module.Module
import com.intellij.openapi.roots.LibraryOrderEntry
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.ModuleRootManager

class PapyrusGameFacet(facetType: FacetType<out Facet<*>, *>, module: Module, name: String, configuration: PapyrusFacetConfiguration, underlyingFacet: Facet<*>?) : LibraryContributingFacet<PapyrusFacetConfiguration>(facetType, module, name, configuration, underlyingFacet) {
    override fun updateLibrary() {
        ApplicationManager.getApplication().runWriteAction {
            val rootManager = ModuleRootManager.getInstance(module)
            val model = rootManager.modifiableModel
            var modelChanged = false
            try {
                val sdk = configuration.sdk
                val name = sdk?.name
                var librarySeen = false
                for(entry in model.orderEntries) {
                    if(entry is LibraryOrderEntry) {
                        val libraryName = entry.libraryName
                        if(name != null && name == libraryName) {
                            librarySeen = true
                            continue
                        }
                        if(libraryName != null && libraryName.endsWith("Game Install")) {
                            model.removeOrderEntry(entry)
                            modelChanged = true
                        }
                    }
                }

                if(name != null) {
                    val provider = ModifiableModelsProvider.SERVICE.getInstance()
                    val libraryTableModifiableModel = provider.libraryTableModifiableModel
                    var library = libraryTableModifiableModel.getLibraryByName(name)
                    provider.disposeLibraryTableModifiableModel(libraryTableModifiableModel)
                    if(library == null)
                        library = PapyrusSdkTableListener.addLibrary(sdk)
                    if(!librarySeen) {
                        model.addLibraryEntry(library)
                        modelChanged = true
                    }
                }
            } finally {
                if(modelChanged) model.commit()
                else model.dispose()
            }
        }
    }

    override fun removeLibrary() {
        ApplicationManager.getApplication().runWriteAction {
            val rootManager = ModuleRootManager.getInstance(module)
            val model = rootManager.modifiableModel
            for(entry in model.orderEntries) {
                if(entry is LibraryOrderEntry) {
                    val library = entry.library
                    if(library != null) {
                        val libraryName = library.name
                        if(libraryName != null && libraryName.endsWith("Game Install")) {
                            model.removeOrderEntry(entry)
                        }
                    }
                }
            }
            model.commit()
        }
    }

    override fun initFacet() {
        updateLibrary()
    }
}