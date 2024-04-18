package com.vcamx.whitebox.server.pm

import android.os.Parcel
import android.os.Parcelable

class WPackage() : Parcelable{
    var baseCodePath: String? = null
    var packageName: String? = null
    constructor(parcel: Parcel) : this() {
        baseCodePath = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(baseCodePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WPackage> {
        override fun createFromParcel(parcel: Parcel): WPackage {
            return WPackage(parcel)
        }

        override fun newArray(size: Int): Array<WPackage?> {
            return arrayOfNulls(size)
        }
    }
}