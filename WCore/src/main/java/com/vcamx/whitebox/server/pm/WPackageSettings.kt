package com.vcamx.whitebox.server.pm

import android.os.Parcel
import android.os.Parcelable
import com.vcamx.whitebox.entity.pm.InstallOption

class WPackageSettings() : Parcelable {
    var appId: Int? = null
    var installOption: InstallOption? = null
    var pkg: WPackage? = null
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WPackageSettings> {
        override fun createFromParcel(parcel: Parcel): WPackageSettings {
            return WPackageSettings(parcel)
        }

        override fun newArray(size: Int): Array<WPackageSettings?> {
            return arrayOfNulls(size)
        }
    }
}