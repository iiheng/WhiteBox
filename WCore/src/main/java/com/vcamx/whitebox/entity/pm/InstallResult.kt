package com.vcamx.whitebox.entity.pm

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class InstallResult(
    var success: Boolean = true,
    var packageName: String? = null,
    var msg: String? = null
) : Parcelable {
    companion object {
        const val TAG = "InstallResult"

        @JvmField
        val CREATOR: Parcelable.Creator<InstallResult> = object : Parcelable.Creator<InstallResult> {
            override fun createFromParcel(source: Parcel): InstallResult = InstallResult(source)
            override fun newArray(size: Int): Array<InstallResult?> = arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        success = parcel.readByte() != 0.toByte(),
        packageName = parcel.readString(),
        msg = parcel.readString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (success) 1 else 0)
        dest.writeString(packageName)
        dest.writeString(msg)
    }

    fun installError(msg: String): InstallResult {
        this.msg = msg
        this.success = false
        Log.d(TAG, msg)
        return this
    }
}
