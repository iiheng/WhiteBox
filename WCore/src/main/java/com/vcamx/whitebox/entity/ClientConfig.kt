package com.vcamx.whitebox.entity

import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable

data class ClientConfig(
    var packageName: String? = null,
    var processName: String? = null,
    var vpid: Int = 0,
    var vuid: Int = 0,
    var uid: Int = 0,
    var userId: Int = 0,
    var baseVUid: Int = 0,
    var token: IBinder? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readStrongBinder()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(packageName)
        dest.writeString(processName)
        dest.writeInt(vpid)
        dest.writeInt(vuid)
        dest.writeInt(uid)
        dest.writeInt(userId)
        dest.writeInt(baseVUid)
        dest.writeStrongBinder(token)
    }

    companion object CREATOR : Parcelable.Creator<ClientConfig> {
        override fun createFromParcel(parcel: Parcel): ClientConfig {
            return ClientConfig(parcel)
        }

        override fun newArray(size: Int): Array<ClientConfig?> {
            return arrayOfNulls(size)
        }
    }
}
