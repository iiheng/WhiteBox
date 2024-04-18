package com.vcamx.whitebox.server.user

import android.os.Parcel
import android.os.Parcelable

class WUserHandle() : Parcelable{
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WUserHandle> {
        // NOTE: keep logic in sync with system/core/libcutils/multiuser.c
        const val PER_USER_RANGE: Int = 100000
        override fun createFromParcel(parcel: Parcel): WUserHandle {
            return WUserHandle(parcel)
        }

        override fun newArray(size: Int): Array<WUserHandle?> {
            return arrayOfNulls(size)
        }
        fun getAppId(uid: Int): Int {
            return uid % PER_USER_RANGE
        }
    }

}