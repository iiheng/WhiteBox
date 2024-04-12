package com.vcamx.whitebox.client.frameworks

import android.os.RemoteException
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.ServiceManager
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.server.user.IWUserManagerService
import com.vcamx.whitebox.server.user.WUserInfo



class WUserManager {
    private var mService: IWUserManagerService? = null

    fun createUser(userId: Int): WUserInfo? {
        try {
            return service.createUser(userId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        return null
    }

    fun deleteUser(userId: Int) {
        try {
            service.deleteUser(userId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }



    val users: List<WUserInfo>
        get() {
            try {
                return service.getUsers()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            return emptyList()
        }

    private fun getService(): IWUserManagerService? {
        if (mService?.asBinder()?.isBinderAlive == true) {
            return mService
        }
        mService = IWUserManagerService.Stub.asInterface(WhiteBoxCore.get().getService(ServiceManager.USER_MANAGER))
        return getService()
    }


    companion object {
        private val sUserManager = WUserManager()
        fun get(): WUserManager {
            return sUserManager
        }
    }
}
