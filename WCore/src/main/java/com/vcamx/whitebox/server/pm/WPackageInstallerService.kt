package com.vcamx.whitebox.server.pm

import com.vcamx.whitebox.server.ISystemService

object WPackageInstallerService : IWPackageInstallerService.Stub(), ISystemService {
    override fun installPackageAsUser(file: WPackageSettings?, userId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun uninstallPackageAsUser(file: WPackageSettings?, removeApp: Boolean, userId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun updatePackage(file: WPackageSettings?): Int {
        TODO("Not yet implemented")
    }

    override fun systemReady() {
        TODO("Not yet implemented")
    }

}