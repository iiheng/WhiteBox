package top.niunaijun.blackboxa.view.main

import android.app.Application
import android.content.Context
import android.util.Log
import com.vcamx.whitebox.App
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.client.ClientConfiguration
import com.vcamx.whitebox.client.hook.AppLifecycleCallback

/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/5/6 23:38
 */
class WhiteBoxLoadCallback {

    fun attachBaseContext(context: Context){

        val sharedPreferences = context.getSharedPreferences(context.packageName+"_preferences",Context.MODE_PRIVATE)

        val isHideRoot = sharedPreferences.getBoolean("root_hide",false)
        val isHideXp = sharedPreferences.getBoolean("xp_hide",false)

        WhiteBoxCore.get().doAttachBaseContext(context, object : ClientConfiguration() {
            override fun getHostPackageName(): String {
                return context.packageName
            }

            override fun isHideRoot(): Boolean {
                return isHideRoot
            }

            override fun isHideXposed(): Boolean {
                return isHideXp
            }
        })
    }


//    fun addLifecycleCallback(){
//        WhiteBoxCore.get().setAppLifecycleCallback(object : AppLifecycleCallback() {
//            override fun beforeCreateApplication(packageName: String?, processName: String?, context: Context?) {
//                Log.d(App.TAG, "beforeCreateApplication: pkg $packageName, processName $processName")
//            }
//
//            override fun beforeApplicationOnCreate(packageName: String?, processName: String?, application: Application?) {
//                Log.d(App.TAG, "beforeApplicationOnCreate: pkg $packageName, processName $processName")
//            }
//
//            override fun afterApplicationOnCreate(packageName: String?, processName: String?, application: Application?) {
//                Log.d(App.TAG, "afterApplicationOnCreate: pkg $packageName, processName $processName")
//            }
//        }
//        )
//
//    }

}