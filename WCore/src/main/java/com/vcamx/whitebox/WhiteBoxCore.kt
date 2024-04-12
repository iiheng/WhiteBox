package com.vcamx.whitebox

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.Process
import com.vcamx.whitebox.client.ClientConfiguration
import com.vcamx.whitebox.client.StubManifest
import com.vcamx.whitebox.client.frameworks.WUserManager
import com.vcamx.whitebox.server.user.WUserInfo
import com.vcamx.whitebox.utils.compat.BundleCompat
import com.vcamx.whitebox.utils.provider.ProviderCall

class WhiteBoxCore : ClientConfiguration() {
    private var sContext: Context? = null
    private val mServices: MutableMap<String, IBinder> = HashMap()
    private var mClientConfiguration: ClientConfiguration? = null
    companion object {
        private val sBlackBoxCore = WhiteBoxCore()

        fun get(): WhiteBoxCore {
            return sBlackBoxCore
        }

        private fun getProcessName(context: Context): String {
            val pid = Process.myPid()
            var processName: String? = null
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (info in am.runningAppProcesses) {
                if (info.pid == pid) {
                    processName = info.processName
                    break
                }
            }
            if (processName == null) {
                throw RuntimeException("processName = null")
            }
            return processName
        }

        fun getHostPkg(): String {
            return get().getHostPackageName()
        }


    }

    override fun getHostPackageName(): String {
        return mClientConfiguration!!.getHostPackageName()
    }

    fun get(): WhiteBoxCore {
        return sBlackBoxCore
    }

    fun getUsers(): List<WUserInfo> {
        return WUserManager.get().users
    }

    fun getService(name: String): IBinder {
        var binder = mServices[name]
        if (binder?.isBinderAlive == true) {
            return binder
        }
        val bundle = Bundle().apply {
            putString("_VM_|_server_name_", name)
        }
        val vm = ProviderCall.callSafely(StubManifest.getBindProvider(), "VM", null, bundle)
        checkNotNull(vm) { "VM bundle should not be null" }
        binder = BundleCompat.getBinder(vm, "_VM_|_server_")
        mServices[name] = binder!!
        return binder
    }

//    fun doAttachBaseContext(context: Context, clientConfiguration: ClientConfiguration?) {
//        clientConfiguration ?: throw IllegalArgumentException("ClientConfiguration is null!")
//        mClientConfiguration = clientConfiguration
//        Reflection.unseal(context)
//        sContext = context
//        val processName = getProcessName(getContext())
//        when {
//            processName == BlackBoxCore.getHostPkg() -> {
//                mProcessType = ProcessType.Main
//                startLogcat()
//                initJarEnv()
//            }
//            processName.endsWith(getContext().getString(R.string.black_box_service_name)) -> {
//                mProcessType = ProcessType.Server
//            }
//            else -> {
//                mProcessType = ProcessType.BAppClient
//            }
//        }
//        BlackBoxCore.get().isVirtualProcess()
//        if (isServerProcess()) {
//            val intent = Intent(getContext(), DaemonService::class.java).apply {
//                if (BuildCompat.isOreo()) {
//                    getContext().startForegroundService(this)
//                } else {
//                    getContext().startService(this)
//                }
//            }
//        }
//        HookManager.get().init()
//    }

    fun getContext(): Context {
        return sContext!!
    }


}
