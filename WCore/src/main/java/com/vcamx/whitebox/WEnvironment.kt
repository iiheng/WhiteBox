package com.vcamx.whitebox

import java.io.File

object WEnvironment {
    private val sVirtualRoot: File = File(WhiteBoxCore.getContext().getCacheDir().getParent(), "virtual")
    var JUNIT_JAR: File = File(getCacheDir(), "junit.jar")
    fun getCacheDir(): File {
        return File(sVirtualRoot, "cache")
    }
}