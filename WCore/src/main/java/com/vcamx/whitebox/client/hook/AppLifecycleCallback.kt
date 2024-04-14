package com.vcamx.whitebox.client.hook

import android.app.Application
import android.content.Context

object AppLifecycleCallback {

    fun beforeCreateApplication(packageName: String, processName: String, context: Context) {

    }

    fun beforeApplicationOnCreate(packageName: String, processName: String, application: Application) {

    }

    fun afterApplicationOnCreate(packageName: String, processName: String, application: Application) {

    }
}
