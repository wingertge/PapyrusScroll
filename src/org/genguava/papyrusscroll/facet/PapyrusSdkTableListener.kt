package org.genguava.papyrusscroll.facet

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.projectRoots.ProjectJdkTable
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.OrderRootType
import com.intellij.openapi.roots.libraries.Library
import com.intellij.util.messages.MessageBus
import org.genguava.papyrusscroll.module.PapyrusSdkType

class PapyrusSdkTableListener(messageBus: MessageBus) : Disposable {
    companion object {
        fun addLibrary(sdk: Sdk): Library {
            val libraryTableModel = ModifiableModelsProvider.SERVICE.getInstance().libraryTableModifiableModel
            val library = libraryTableModel.createLibrary(sdk.name)
            val model = library.modifiableModel
            for(url in sdk.rootProvider.getUrls(OrderRootType.CLASSES)) {
                model.addRoot(url, OrderRootType.CLASSES)
                model.addRoot(url, OrderRootType.SOURCES)
            }
            model.commit()
            libraryTableModel.commit()
            return library
        }

        fun removeLibrary(sdk: Sdk) {
            ApplicationManager.getApplication().invokeLater({
                ApplicationManager.getApplication().runWriteAction {
                    val libraryTableModel = ModifiableModelsProvider.SERVICE.getInstance().libraryTableModifiableModel
                    val library = libraryTableModel.getLibraryByName(sdk.name)
                    if(library != null)
                        libraryTableModel.removeLibrary(library)
                    libraryTableModel.commit()
                }
            }, ModalityState.NON_MODAL)
        }

        fun renameLibrary(sdk: Sdk, previousName: String) {
            ApplicationManager.getApplication().invokeLater({
                val libraryTableModel = ModifiableModelsProvider.SERVICE.getInstance().libraryTableModifiableModel
                val library = libraryTableModel.getLibraryByName(previousName)
                if(library != null) {
                    val model = library.modifiableModel
                    model.name = sdk.name
                    model.commit()
                }
                libraryTableModel.commit()
            }, ModalityState.NON_MODAL)
        }
    }

    override fun dispose() {

    }

    init {
        val listener = object: ProjectJdkTable.Listener {
            override fun jdkAdded(sdk: Sdk) {
                if(sdk.sdkType is PapyrusSdkType) {
                    ApplicationManager.getApplication().invokeLater {
                        ApplicationManager.getApplication().runWriteAction {
                            addLibrary(sdk)
                        }
                    }
                }
            }

            override fun jdkNameChanged(sdk: Sdk, previousName: String) {
                if(sdk.sdkType is PapyrusSdkType) renameLibrary(sdk, previousName)
            }

            override fun jdkRemoved(sdk: Sdk) {
                if(sdk.sdkType is PapyrusSdkType) removeLibrary(sdk)
            }
        }
        messageBus.connect().subscribe(ProjectJdkTable.JDK_TABLE_TOPIC, listener)
    }
}