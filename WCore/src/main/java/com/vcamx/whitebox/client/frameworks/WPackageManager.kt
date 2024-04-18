package com.vcamx.whitebox.client.frameworks

import android.content.pm.PackageInfo
import android.os.RemoteException
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.server.ServiceManager
import com.vcamx.whitebox.server.pm.IWPackageManagerService

object WPackageManager {
    fun getPackageInfo(packageName: String, flags: Int, userId: Int): PackageInfo? {
        return try {
            getService()?.getPackageInfo(packageName, flags, userId)
        } catch (e: RemoteException) {
            null
        }
    }


//    fun getApplicationInfo(packageName: String?, getMetaData: Int, userId: Int): Any {
//
//    }

    private var mService: IWPackageManagerService? = null
    private fun getService(): IWPackageManagerService? {
        if (mService?.asBinder()?.isBinderAlive == true) {
            return mService
        }
        mService = IWPackageManagerService.Stub.asInterface(WhiteBoxCore.get().getService(ServiceManager.PACKAGE_MANAGER))
        return getService()
    }

}