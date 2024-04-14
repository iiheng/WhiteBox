package com.vcamx.whitebox

import android.app.Application
import android.content.Context
import com.vcamx.whitebox.utils.compat.BuildCompat
import top.niunaijun.blackboxa.view.main.WhiteBoxLoadCallback

class App : Application() {
    companion object {
        private lateinit var context: Context

        fun getInstance(): Context {
            return context
        }

        const val TAG = "BApplication"
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = base!!

        try {

            val callback = WhiteBoxLoadCallback()
            callback.attachBaseContext(context)
//            callback.addLifecycleCallback()

//            if(BuildCompat.isR()){
//                WhiteBoxCore.get().isXPEnable = false
//            }
            //android 11 先屏蔽xp

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreate() {
        super.onCreate()
//        UMConfigure.init(this,,BuildConfig.FLAVOR,0,String pushSecret);
        WhiteBoxCore.get().doCreate()
    }
}