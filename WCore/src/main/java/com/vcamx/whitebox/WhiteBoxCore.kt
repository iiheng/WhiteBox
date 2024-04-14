package com.vcamx.whitebox

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.IBinder
import android.os.Process
import com.vcamx.wcore.R
import com.vcamx.whitebox.WEnvironment.JUNIT_JAR
import com.vcamx.whitebox.client.ClientConfiguration
import com.vcamx.whitebox.client.StubManifest
import com.vcamx.whitebox.client.frameworks.WUserManager
import com.vcamx.whitebox.client.hook.AppLifecycleCallback
import com.vcamx.whitebox.client.hook.delegate.ContentProviderDelegate
import com.vcamx.whitebox.server.DaemonService
import com.vcamx.whitebox.server.user.WUserInfo
import com.vcamx.whitebox.utils.FileUtils
import com.vcamx.whitebox.utils.ShellUtils
import com.vcamx.whitebox.utils.compat.BuildCompat
import com.vcamx.whitebox.utils.compat.BundleCompat
import com.vcamx.whitebox.utils.provider.ProviderCall
import me.weishu.reflection.Reflection
import java.io.File
import java.io.IOException
import java.io.InputStream

class WhiteBoxCore : ClientConfiguration() {

    private val mServices: MutableMap<String, IBinder> = HashMap()
    private var mClientConfiguration: ClientConfiguration? = null
    private var mProcessType: ProcessType? = null
    private var mAppLifecycleCallback: AppLifecycleCallback = AppLifecycleCallback
    companion object {
        private var sContext: Context? = null
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

        fun getContext(): Context {
            return sContext!!
        }
//        fun setXPEnable(enable: Boolean) {
//            BXposedManager.get().setXPEnable(enable)
//        }

    }
    private enum class ProcessType {
        /**
         * Server process
         */
        Server,

        /**
         * Virtual app process
         */
        BAppClient,

        /**
         * Main process
         */
        Main,
    }

    fun setAppLifecycleCallback(appLifecycleCallback: AppLifecycleCallback?) {
        requireNotNull(appLifecycleCallback) { "AppLifecycleCallback is null!" }
        mAppLifecycleCallback = appLifecycleCallback
    }

    override fun getHostPackageName(): String {
        return mClientConfiguration?.getHostPackageName() ?: ""
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

    fun doAttachBaseContext(context: Context, clientConfiguration: ClientConfiguration?) {
        clientConfiguration ?: throw IllegalArgumentException("ClientConfiguration is null!")
        mClientConfiguration = clientConfiguration
        Reflection.unseal(context)
        sContext = context
        val processName = getProcessName(getContext())
        when {
            processName == getHostPkg() -> {
                mProcessType = ProcessType.Main
                startLogcat()
                initJarEnv()
            }
            processName.endsWith(getContext().getString(R.string.black_box_service_name)) -> {
                mProcessType = ProcessType.Server
            }
            else -> {
                mProcessType = ProcessType.BAppClient
            }
        }
        get().isVirtualProcess()
        if (isServerProcess()) {
            val intent = Intent(getContext(), DaemonService::class.java).apply {
                if (BuildCompat.isOreo()) {
                    getContext().startForegroundService(this)
                } else {
                    getContext().startService(this)
                }
            }
        }
//        HookManager.get().init()
    }
    fun doCreate() {
        // fix contentProvider
        if (isVirtualProcess()) {
            ContentProviderDelegate.init()
        }
        if (!isServerProcess()) {
            initService()
        }
    }
    fun isVirtualProcess(): Boolean {
        return mProcessType == ProcessType.BAppClient
    }
    private fun initJarEnv() {
        try {
            val open: InputStream = getContext().getAssets().open("junit.jar")
            FileUtils.copyFile(open, JUNIT_JAR)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun isServerProcess(): Boolean {
        return mProcessType == ProcessType.Server
    }
    private fun startLogcat() {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            getContext().getPackageName() + "_logcat.txt"
        )
        FileUtils.deleteDir(file)
        ShellUtils.execCommand("logcat -c", false)
        ShellUtils.execCommand("logcat >> " + file.absolutePath + " &", false)
    }
}
