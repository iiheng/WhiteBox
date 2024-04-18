package com.vcamx.whitebox.client.frameworks

import android.os.RemoteException
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.server.ServiceManager
import com.vcamx.whitebox.server.user.IWUserManagerService
import com.vcamx.whitebox.server.user.WUserInfo



object WUserManager {
    private var mService: IWUserManagerService? = null

    fun createUser(userId: Int): WUserInfo? {
        try {
            return getService()!!.createUser(userId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        return null
    }

    fun deleteUser(userId: Int) {
        try {
            getService()!!.deleteUser(userId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }



    val users: List<WUserInfo>
        get() {
            try {
                return getService()!!.getUsers()
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

}
