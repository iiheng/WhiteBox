package com.vcamx.whitebox.utils.compat

import android.os.Build

object BuildCompat {
    fun isOreo(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O || (Build.VERSION.SDK_INT >= 25 && Build.VERSION.PREVIEW_SDK_INT == 1)
    }

    fun isR(): Boolean {
        return Build.VERSION.SDK_INT >= 30 || (Build.VERSION.SDK_INT >= 29 && Build.VERSION.PREVIEW_SDK_INT == 1)
    }
}