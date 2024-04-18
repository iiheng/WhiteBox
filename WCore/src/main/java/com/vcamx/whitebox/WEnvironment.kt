package com.vcamx.whitebox

import com.vcamx.whitebox.utils.FileUtils
import java.io.File

object WEnvironment {
    private val sVirtualRoot: File = File(WhiteBoxCore.getContext().getCacheDir().getParent(), "virtual")

    private val sExternalVirtualRoot: File = WhiteBoxCore.getContext().getExternalFilesDir("virtual")!!

    var JUNIT_JAR: File = File(getCacheDir(), "junit.jar")
    private fun getCacheDir(): File {
        return File(sVirtualRoot, "cache")
    }

    fun load() {
        FileUtils.mkdirs(sVirtualRoot)
        FileUtils.mkdirs(sExternalVirtualRoot)
        FileUtils.mkdirs(getSystemDir())
        FileUtils.mkdirs(getCacheDir())
    }

    fun getSystemDir(): File {
        return File(sVirtualRoot, "system")
    }

    fun getAppRootDir(): File {
        return getAppDir("")
    }

    private fun getAppDir(packageName: String): File {
        return File(sVirtualRoot, "data/app/$packageName")
    }

    fun getPackageConf(packageName: String): File {
        return File(getAppDir(packageName), "package.conf")
    }
}