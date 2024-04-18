package com.vcamx.whitebox.server.am

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ProviderInfo
import android.os.Bundle
import android.os.IBinder
import com.vcamx.whitebox.entity.ClientConfig
import com.vcamx.whitebox.entity.UnbindRecord
import com.vcamx.whitebox.server.ISystemService

object WActivityManagerService : IWActivityManagerService.Stub(), ISystemService {
    override fun initProcess(
        packageName: String?,
        processName: String?,
        userId: Int
    ): ClientConfig {
        TODO("Not yet implemented")
    }

    override fun restartProcess(packageName: String?, processName: String?, userId: Int) {
        TODO("Not yet implemented")
    }

    override fun startActivity(intent: Intent?, userId: Int) {
        TODO("Not yet implemented")
    }

    override fun startActivityAms(
        userId: Int,
        intent: Intent?,
        resolvedType: String?,
        resultTo: IBinder?,
        resultWho: String?,
        requestCode: Int,
        flags: Int,
        options: Bundle?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun startActivities(
        userId: Int,
        intent: Array<out Intent>?,
        resolvedType: Array<out String>?,
        resultTo: IBinder?,
        options: Bundle?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun startService(intent: Intent?, resolvedType: String?, userId: Int): ComponentName {
        TODO("Not yet implemented")
    }

    override fun stopService(intent: Intent?, resolvedType: String?, userId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun bindService(service: Intent?, binder: IBinder?, resolvedType: String?, userId: Int): Intent {
        TODO("Not yet implemented")
    }

    override fun unbindService(binder: IBinder?, userId: Int) {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(proxyIntent: Intent?, userId: Int) {
        TODO("Not yet implemented")
    }

    override fun onServiceUnbind(proxyIntent: Intent?, userId: Int): UnbindRecord {
        TODO("Not yet implemented")
    }

    override fun onServiceDestroy(proxyIntent: Intent?, userId: Int) {
        TODO("Not yet implemented")
    }

    override fun acquireContentProviderClient(providerInfo: ProviderInfo?): IBinder {
        TODO("Not yet implemented")
    }

    override fun sendBroadcast(intent: Intent?, resolvedType: String?, userId: Int): Intent {
        TODO("Not yet implemented")
    }

    override fun peekService(intent: Intent?, resolvedType: String?, userId: Int): IBinder {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(taskId: Int, token: IBinder?, activityRecord: IBinder?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResumed(token: IBinder?) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(token: IBinder?) {
        TODO("Not yet implemented")
    }

    override fun onFinishActivity(token: IBinder?) {
        TODO("Not yet implemented")
    }

    override fun systemReady() {
        TODO("Not yet implemented")
    }

}