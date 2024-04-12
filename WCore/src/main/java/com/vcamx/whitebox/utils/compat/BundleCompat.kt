package com.vcamx.whitebox.utils.compat

import android.os.Build
import android.os.Bundle
import android.os.IBinder

class BundleCompat {
    companion object {
        fun getBinder(bundle: Bundle, key: String?): IBinder? {
            return if (Build.VERSION.SDK_INT >= 18) {
                bundle.getBinder(key)
            } else {
                // 使用反射来调用Bundle的getIBinder方法
                val getIBinderMethod = Bundle::class.java.getMethod("getIBinder", String::class.java)
                getIBinderMethod.invoke(bundle, key) as? IBinder
            }
        }

    }
}