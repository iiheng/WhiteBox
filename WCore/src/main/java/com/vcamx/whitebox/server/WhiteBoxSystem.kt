package com.vcamx.whitebox.server

import android.content.pm.PackageManager
import com.vcamx.whitebox.WEnvironment
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.server.pm.WPackageManagerService

object WhiteBoxSystem {
    fun startup() {
        // 初始化存储数据的文件夹
        WEnvironment.load()

//        WPackageManagerService.systemReady()
//        BUserManagerService.get().systemReady()
//        BActivityManagerService.get().systemReady()
//        BJobManagerService.get().systemReady()
//        BStorageManagerService.get().systemReady()
//        BPackageInstallerService.get().systemReady()
//        BXposedManagerService.get().systemReady()
//
//        val preInstallPackages = ClientSystemEnv.getPreInstallPackages()
//        preInstallPackages.forEach { preInstallPackage ->
//            try {
//                val packageInfo = WhiteBoxCore.getPackageManager().getPackageInfo(preInstallPackage, 0)
//                WPackageManagerService.installPackageAsUser(packageInfo.applicationInfo.sourceDir, InstallOption.installBySystem(), BUserHandle.USER_ALL)
//            } catch (ignored: PackageManager.NameNotFoundException) {
//                // 忽略找不到包的异常
//            }
//        }
    }
}
