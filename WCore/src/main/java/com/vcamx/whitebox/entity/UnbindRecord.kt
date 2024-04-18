package com.vcamx.whitebox.entity

import android.os.Parcel
import android.os.Parcelable
import android.content.ComponentName

class UnbindRecord(
    private var mBindCount: Int = 0,
    private var mStartId: Int = 0,
    private var mComponentName: ComponentName? = null
) : Parcelable {

    var bindCount: Int
        get() = mBindCount
        set(value) {
            mBindCount = value
        }

    var startId: Int
        get() = mStartId
        set(value) {
            mStartId = value
        }

    var componentName: ComponentName?
        get() = mComponentName
        set(value) {
            mComponentName = value
        }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(ComponentName::class.java.classLoader)
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(mBindCount)
        dest.writeInt(mStartId)
        dest.writeParcelable(mComponentName, flags)
    }

    companion object CREATOR : Parcelable.Creator<UnbindRecord> {
        override fun createFromParcel(parcel: Parcel): UnbindRecord {
            return UnbindRecord(parcel)
        }

        override fun newArray(size: Int): Array<UnbindRecord?> {
            return arrayOfNulls(size)
        }
    }
}
