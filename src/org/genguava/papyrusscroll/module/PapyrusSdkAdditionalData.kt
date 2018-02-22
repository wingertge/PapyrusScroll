package org.genguava.papyrusscroll.module

import com.intellij.openapi.projectRoots.SdkAdditionalData
import org.jdom.Element

abstract class PapyrusSdkAdditionalData : SdkAdditionalData {
    abstract fun save(additional: Element)
}