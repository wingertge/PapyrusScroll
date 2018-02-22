package org.genguava.papyrusscroll.facet

import com.intellij.facet.FacetConfiguration
import com.intellij.openapi.projectRoots.ProjectJdkTable
import org.jdom.Element

abstract class PapyrusFacetConfiguration : PapyrusFacetSettings(), FacetConfiguration {
    companion object {
        const val SDK_NAME = "sdkName"
    }

    abstract val sdkTypeName: String

    override fun readExternal(element: Element) {
        val sdkName = element.getAttributeValue(SDK_NAME)
        sdk = if(sdkName.isNullOrEmpty()) null else ProjectJdkTable.getInstance().findJdk(sdkName, sdkTypeName)
    }

    override fun writeExternal(element: Element) {
        element.setAttribute(SDK_NAME, if(sdk != null) sdk!!.name else "")
    }
}