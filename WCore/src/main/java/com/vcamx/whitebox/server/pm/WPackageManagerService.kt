package com.vcamx.whitebox.server.pm

import android.content.ComponentName
import android.content.Intent
import android.content.pm.*
import com.vcamx.whitebox.entity.pm.InstallOption
import com.vcamx.whitebox.entity.pm.InstallResult
import com.vcamx.whitebox.entity.pm.InstalledPackage
import com.vcamx.whitebox.server.ISystemService

object WPackageManagerService: IWPackageManagerService.Stub(), ISystemService {
    private val mSettings: Settings = Settings()
    val mPackages: Map<String, WPackageSettings> = mSettings.mPackages
    fun getAppId(packageName: String): Int {
        val wPackageSettings = mPackages[packageName]
        return wPackageSettings?.appId ?: -1
    }


    override fun resolveService(intent: Intent?, flags: Int, resolvedType: String?, userId: Int): ResolveInfo {
        TODO("Not yet implemented")
    }

    override fun resolveActivity(intent: Intent?, flags: Int, resolvedType: String?, userId: Int): ResolveInfo {
        TODO("Not yet implemented")
    }

    override fun resolveContentProvider(authority: String?, flag: Int, userId: Int): ProviderInfo {
        TODO("Not yet implemented")
    }

    override fun resolveIntent(intent: Intent?, resolvedType: String?, flags: Int, userId: Int): ResolveInfo {
        TODO("Not yet implemented")
    }

    override fun getApplicationInfo(packageName: String?, flags: Int, userId: Int): ApplicationInfo {
        TODO("Not yet implemented")
    }

    override fun getPackageInfo(packageName: String?, flags: Int, userId: Int): PackageInfo {
        TODO("Not yet implemented")
    }

    override fun getServiceInfo(component: ComponentName?, flags: Int, userId: Int): ServiceInfo {
        TODO("Not yet implemented")
    }

    override fun getReceiverInfo(componentName: ComponentName?, flags: Int, userId: Int): ActivityInfo {
        TODO("Not yet implemented")
    }

    override fun getActivityInfo(component: ComponentName?, flags: Int, userId: Int): ActivityInfo {
        TODO("Not yet implemented")
    }

    override fun getProviderInfo(component: ComponentName?, flags: Int, userId: Int): ProviderInfo {
        TODO("Not yet implemented")
    }

    override fun getInstalledApplications(flags: Int, userId: Int): MutableList<ApplicationInfo> {
        TODO("Not yet implemented")
    }

    override fun getInstalledPackages(flags: Int, userId: Int): MutableList<PackageInfo> {
        TODO("Not yet implemented")
    }

    override fun queryIntentActivities(
        intent: Intent?,
        flags: Int,
        resolvedType: String?,
        userId: Int
    ): MutableList<ResolveInfo> {
        TODO("Not yet implemented")
    }

    override fun queryBroadcastReceivers(
        intent: Intent?,
        flags: Int,
        resolvedType: String?,
        userId: Int
    ): MutableList<ResolveInfo> {
        TODO("Not yet implemented")
    }

    override fun queryContentProviders(
        processName: String?,
        uid: Int,
        flags: Int,
        userId: Int
    ): MutableList<ProviderInfo> {
        TODO("Not yet implemented")
    }

    override fun installPackageAsUser(file: String?, option: InstallOption?, userId: Int): InstallResult {
        TODO("Not yet implemented")
    }

    override fun uninstallPackageAsUser(packageName: String?, userId: Int) {
        TODO("Not yet implemented")
    }

    override fun uninstallPackage(packageName: String?) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(userId: Int) {
        TODO("Not yet implemented")
    }

    override fun isInstalled(packageName: String?, userId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getInstalledPackagesAsUser(userId: Int): MutableList<InstalledPackage> {
        TODO("Not yet implemented")
    }

    override fun systemReady() {
//        mSettings.scanPackage()
//        for (value in mPackages.values) {
//            mComponentResolver.addAllComponents(value.pkg)
//        }
    }

}