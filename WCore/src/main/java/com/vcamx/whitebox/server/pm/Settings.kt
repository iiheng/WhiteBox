package com.vcamx.whitebox.server.pm

import android.content.pm.PackageManager
import android.os.Parcel
import android.util.ArrayMap
import com.vcamx.whitebox.WEnvironment
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.entity.pm.InstallOption
import com.vcamx.whitebox.server.WProcessManager
import com.vcamx.whitebox.utils.FileUtils
import java.io.File

class Settings {
    val mPackages: ArrayMap<String, WPackageSettings> = ArrayMap<String, WPackageSettings>()
    fun scanPackage() {
        synchronized(mPackages) {
            val appRootDir: File = WEnvironment.getAppRootDir()
            FileUtils.mkdirs(appRootDir)
            val apps = appRootDir.listFiles()
            for (app in apps) {
                if (!app.isDirectory) {
                    continue
                }
//                updatePackageLP(app)
            }
        }
    }

//    private fun updatePackageLP(app: File) {
//        val packageName = app.name
//        val packageSettingsIn = Parcel.obtain()
//        val packageConf = WEnvironment.getPackageConf(packageName)
//        try {
//            val wPackageSettingsBytes = FileUtils.toByteArray(packageConf)
//
//            packageSettingsIn.unmarshall(wPackageSettingsBytes, 0, wPackageSettingsBytes.size)
//            packageSettingsIn.setDataPosition(0)
//
//            val wPackageSettings = WPackageSettings(packageSettingsIn)
//            if (wPackageSettings.installOption!!.isFlag(InstallOption.FLAG_SYSTEM)) {
//                val packageInfo = WhiteBoxCore.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA)
//                val currPackageSourcePath = packageInfo.applicationInfo.sourceDir
//                if (currPackageSourcePath != wPackageSettings.pkg!!.baseCodePath) {
//                    // 更新 baseCodePath 并重新安装
//                    wPackageSettings.pkg!!.packageName?.let { WProcessManager.killAllByPackageName(it) }
//                    wPackageSettings.pkg!!.baseCodePath = currPackageSourcePath
//                    WPackageInstallerService.updatePackage(wPackageSettings)
//                }
//            }
//            wPackageSettings.pkg.mExtras = wPackageSettings
//            wPackageSettings.pkg.applicationInfo = PackageManagerCompat.generateApplicationInfo(wPackageSettings.pkg, 0, BPackageUserState.create(), 0)
//            wPackageSettings.save()
//            mPackages.put(wPackageSettings.pkg.packageName, wPackageSettings)
//            Slog.d(TAG, "loaded Package: $packageName")
//        } catch (e: Throwable) {
//            e.printStackTrace()
//            // 错误的包
//            FileUtils.deleteDir(app)
//            mPackages.remove(packageName)
//            BProcessManager.get().killAllByPackageName(packageName)
//            BPackageManagerService.get().onPackageUninstalled(packageName, BUserHandle.USER_ALL)
//            Slog.d(TAG, "bad Package: $packageName")
//        } finally {
//            packageSettingsIn.recycle()
//        }
//    }

}