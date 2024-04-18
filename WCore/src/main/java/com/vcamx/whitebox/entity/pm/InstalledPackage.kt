package com.vcamx.whitebox.entity.pm

import android.content.pm.PackageManager
import android.os.Parcel
import android.os.Parcelable
import com.vcamx.whitebox.WhiteBoxCore

data class InstalledPackage(
    var userId: Int = 0,
    var packageName: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        userId = parcel.readInt(),
        packageName = parcel.readString()
    )

//    constructor(packageName: String) : this(packageName = packageName)

//    fun getApplication() =
//        WhiteBoxCore.getWPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA, userId)
//
//    fun getPackageInfo() =
//        WhiteBoxCore.getWPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA, userId)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(packageName)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<InstalledPackage> {
        override fun createFromParcel(parcel: Parcel): InstalledPackage = InstalledPackage(parcel)

        override fun newArray(size: Int): Array<InstalledPackage?> = arrayOfNulls(size)
    }
}
