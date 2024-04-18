package com.vcamx.whitebox.entity.pm

import android.os.Parcel
import android.os.Parcelable

class InstallOption(var flags: Int = 0) : Parcelable {
    companion object {
        const val FLAG_SYSTEM = 1
        const val FLAG_STORAGE = 1 shl 1
        const val FLAG_XPOSED = 1 shl 2
        const val FLAG_URI_FILE = 1 shl 3

        @JvmField
        val CREATOR: Parcelable.Creator<InstallOption> = object : Parcelable.Creator<InstallOption> {
            override fun createFromParcel(source: Parcel): InstallOption = InstallOption(source)
            override fun newArray(size: Int): Array<InstallOption?> = arrayOfNulls(size)
        }

        fun installBySystem(): InstallOption = InstallOption().apply { flags = flags or FLAG_SYSTEM }
        fun installByStorage(): InstallOption = InstallOption().apply { flags = flags or FLAG_STORAGE }
    }

    fun makeXposed(): InstallOption = apply { flags = flags or FLAG_XPOSED }
    fun makeUriFile(): InstallOption = apply { flags = flags or FLAG_URI_FILE }
    fun isFlag(flag: Int): Boolean = flags and flag != 0

    private constructor(parcel: Parcel) : this(parcel.readInt())

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.flags)
    }
}
