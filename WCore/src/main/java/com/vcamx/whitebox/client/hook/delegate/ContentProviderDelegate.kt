package com.vcamx.whitebox.client.hook.delegate

import android.net.Uri
import android.os.Build
import android.os.IInterface
import android.util.ArrayMap
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.utils.compat.BuildCompat
import java.lang.reflect.Proxy
import mirror.android.app.ActivityThread
import mirror.android.app.IActivityManager
import mirror.android.content.ContentProviderHolderOreo
import mirror.android.providers.Settings
import java.util.HashSet

/**
 * Created by Milk on 3/31/21.
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
object ContentProviderDelegate {
    const val TAG = "ContentProviderDelegate"
    private val sInjected = HashSet<String>()

    @JvmStatic
    fun update(holder: Any, auth: String) {
        var iInterface: IInterface? = if (BuildCompat.isOreo) {
            ContentProviderHolderOreo.provider[holder]
        } else {
            IActivityManager.ContentProviderHolder.provider[holder]
        }

        if (iInterface is Proxy)
            return

        val vContentProvider = when (auth) {
            "settings" -> SettingsProviderStub().wrapper(iInterface, BlackBoxCore.getHostPkg())
            else -> ContentProviderStub().wrapper(iInterface, BlackBoxCore.getHostPkg())
        }

        if (BuildCompat.isOreo) {
            ContentProviderHolderOreo.provider[holder] = vContentProvider
        } else {
            IActivityManager.ContentProviderHolder.provider[holder] = vContentProvider
        }
    }

    @JvmStatic
    fun init() {
        clearSettingProvider()

        WhiteBoxCore.getContext().contentResolver.call(Uri.parse("content://settings"), "", null, null)
        val activityThread = WhiteBoxCore.mainThread()
        val map = ActivityThread.mProviderMap[activityThread] as ArrayMap<Any, Any>

        map.values.forEach { value ->
            val mNames = ActivityThread.ProviderClientRecordP.mNames[value]
            if (mNames == null || mNames.isEmpty()) {
                return@forEach
            }
            val providerName = mNames[0]
            if (!sInjected.contains(providerName)) {
                sInjected.add(providerName)
                val iInterface = ActivityThread.ProviderClientRecordP.mProvider[value]
                ActivityThread.ProviderClientRecordP.mProvider[value] = ContentProviderStub().wrapper(iInterface, BlackBoxCore.getHostPkg())
                ActivityThread.ProviderClientRecordP.mNames[value] = arrayOf(providerName)
            }
        }
    }

    @JvmStatic
    fun clearSettingProvider() {
        var cache: Any?
        cache = Settings.System.sNameValueCache.get()
        cache?.let { clearContentProvider(it) }
        cache = Settings.Secure.sNameValueCache.get()
        cache?.let { clearContentProvider(it) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && Settings.Global.TYPE != null) {
            cache = Settings.Global.sNameValueCache.get()
            cache?.let { clearContentProvider(it) }
        }
    }

    private fun clearContentProvider(cache: Any) {
        if (BuildCompat.isOreo()) {
            val holder = Settings.NameValueCacheOreo.mProviderHolder[cache]
            if (holder != null) {
                Settings.ContentProviderHolder.mContentProvider[holder] = null
            }
        } else {
            Settings.NameValueCache.mContentProvider[cache] = null
        }
    }
}
