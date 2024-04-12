package com.vcamx.whitebox.client

import com.vcamx.whitebox.WhiteBoxCore

class StubManifest {
    companion object {
        fun getBindProvider(): String {
            return WhiteBoxCore.getHostPkg() + ".blackbox.BindProvider"
        }
    }

}