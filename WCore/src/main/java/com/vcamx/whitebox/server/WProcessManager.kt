package com.vcamx.whitebox.server

import com.vcamx.whitebox.server.pm.WPackageManagerService
import com.vcamx.whitebox.server.user.WUserHandle

object WProcessManager {
    private val mProcessLock = Any()

    private val mProcessMap: MutableMap<Int, MutableMap<String, ProcessRecord>> = hashMapOf()


    private val mPidsSelfLocked: MutableList<ProcessRecord> = ArrayList()

    fun killAllByPackageName(packageName: String) {
        synchronized(mProcessLock) {
            synchronized(mPidsSelfLocked) {
                val tmp = ArrayList(mPidsSelfLocked)
                val appId = WPackageManagerService.getAppId(packageName)
                for (processRecord in mPidsSelfLocked) {
                    val appId1 = WUserHandle.getAppId(processRecord.vuid)
                    if (appId == appId1) {
                        mProcessMap.remove(processRecord.vuid)
                        tmp.remove(processRecord)
                        processRecord.kill()
                    }
                }
                mPidsSelfLocked.clear()
                mPidsSelfLocked.addAll(tmp)
            }
        }
    }

}