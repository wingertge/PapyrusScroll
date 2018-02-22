package org.genguava.papyrusscroll.facet

import com.intellij.facet.*
import com.intellij.openapi.module.Module
import com.intellij.openapi.util.Disposer

abstract class LibraryContributingFacet<T : FacetConfiguration>(facetType: FacetType<out Facet<*>, *>, module: Module, name: String, configuration: T, underlyingFacet: Facet<*>?) : Facet<T>(facetType, module, name, configuration, underlyingFacet) {
    init {
        val connection = module.messageBus.connect()
        connection.subscribe(FacetManager.FACETS_TOPIC, object : FacetManagerAdapter() {
            override fun beforeFacetRemoved(facet: Facet<*>) {
                if(facet == this@LibraryContributingFacet)
                    (facet as LibraryContributingFacet<*>).removeLibrary()
            }

            override fun facetConfigurationChanged(facet: Facet<*>) {
                if(facet == this@LibraryContributingFacet)
                    (facet as LibraryContributingFacet<*>).updateLibrary()
            }
        })
        Disposer.register(this, connection)
    }

    abstract fun updateLibrary()
    abstract fun removeLibrary()
}