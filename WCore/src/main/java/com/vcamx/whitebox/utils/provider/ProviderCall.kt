package com.vcamx.whitebox.utils.provider

import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.vcamx.whitebox.WhiteBoxCore
import com.vcamx.whitebox.utils.compat.ContentProviderCompat

class ProviderCall {
    companion object {
        fun callSafely(authority: String, methodName: String, arg: String?, bundle: Bundle?): Bundle? {
            return try {
                call(authority, WhiteBoxCore.getContext(), methodName, arg, bundle, 5)
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
                null
            }
        }


        @Throws(IllegalAccessException::class)
        fun call(
            authority: String,
            context: Context,
            method: String?,
            arg: String?,
            bundle: Bundle?,
            retryCount: Int
        ): Bundle? {
            val uri = Uri.parse("content://$authority")
            return ContentProviderCompat.call(context, uri, method, arg, bundle, retryCount)
        }


    }
}