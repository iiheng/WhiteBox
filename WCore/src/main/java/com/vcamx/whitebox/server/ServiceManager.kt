package com.vcamx.whitebox.server

import android.os.IBinder
import com.vcamx.whitebox.server.am.WActivityManagerService

object ServiceManager {
    val mCaches: MutableMap<String, IBinder> = hashMapOf()

    init {
//        mCaches[ACTIVITY_MANAGER] = WActivityManagerService
//        mCaches[JOB_MANAGER] = BJobManagerService.get()
//        mCaches[PACKAGE_MANAGER] = BPackageManagerService.get()
//        mCaches[STORAGE_MANAGER] = BStorageManagerService.get()
//        mCaches[USER_MANAGER] = BUserManagerService.get()
//        mCaches[XPOSED_MANAGER] = BXposedManagerService.get()
    }

    const val ACTIVITY_MANAGER = "activity_manager"
    const val JOB_MANAGER = "job_manager"
    const val PACKAGE_MANAGER = "package_manager"
    const val STORAGE_MANAGER = "storage_manager"
    const val USER_MANAGER = "user_manager"
//    const val XPOSED_MANAGER = "Xposed_manager"

    fun getService(name: String): IBinder? = mCaches[name]
}
