package com.vcamx.whitebox.client

abstract class ClientConfiguration {

    open fun isHideRoot(): Boolean = false

    open fun isHideXposed(): Boolean = false

    abstract fun getHostPackageName(): String

}
