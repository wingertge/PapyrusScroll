package org.genguava.papyrusscroll.module

abstract class PapyrusRepository(val friendlyName: String, open val url: String, val supportsListOp: Boolean = false) {
    fun listPackages(): List<PapyrusPackage> {
        TODO("not implemented")
    }

    fun fetchPackage(path: String, version: PapyrusPackageVersion): PapyrusPackage? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}