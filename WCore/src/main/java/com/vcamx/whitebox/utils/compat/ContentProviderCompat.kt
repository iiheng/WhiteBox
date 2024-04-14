package com.vcamx.whitebox.utils.compat

import android.content.ContentProviderClient
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.RemoteException
import android.os.SystemClock

class ContentProviderCompat {

    companion object {
        @Throws(IllegalAccessException::class)
        fun call(
            context: Context,
            uri: Uri,
            method: String?,
            arg: String?,
            extras: Bundle?,
            retryCount: Int
        ): Bundle? {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return context.contentResolver.call(uri, method!!, arg, extras)
            }
            val client: ContentProviderClient? = acquireContentProviderClientRetry(
                    context,
                    uri,
                    retryCount
                )
            try {
                if (client == null) {
                    throw IllegalAccessException()
                }
                return client.call(method!!, arg, extras)
            } catch (e: RemoteException) {
                throw IllegalAccessException(e.message)
            } finally {
                releaseQuietly(client)
            }
        }


        fun acquireContentProviderClientRetry(context: Context, uri: Uri, retryCount: Int): ContentProviderClient? {
            var client: ContentProviderClient? = acquireContentProviderClient(context, uri)
            if (client == null) {
                var retry = 0
                while (retry < retryCount && client == null) {
                    SystemClock.sleep(100)
                    retry++
                    client = acquireContentProviderClient(
                        context,
                        uri
                    )
                }
            }
            return client
        }

        private fun acquireContentProviderClient(context: Context, uri: Uri): ContentProviderClient? {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    return context.contentResolver.acquireUnstableContentProviderClient(uri)
                }
                return context.contentResolver.acquireContentProviderClient(uri)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
            return null
        }

        private fun releaseQuietly(client: ContentProviderClient?) {
            if (client != null) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        client.close()
                    } else {
                        client.release()
                    }
                } catch (ignored: Exception) {
                }
            }
        }

    }
}