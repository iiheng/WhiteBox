package com.vcamx.whitebox.server.user

import android.os.Parcel
import android.os.Parcelable

class WUserInfo : Parcelable {
    var id: Int = 0
    var status: BUserStatus? = null
    var name: String? = null
    var createTime: Long = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeInt(if (this.status == null) -1 else status!!.ordinal)
        dest.writeString(this.name)
        dest.writeLong(this.createTime)
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        val tmpStatus = `in`.readInt()
        this.status = if (tmpStatus == -1) null else BUserStatus.values().get(tmpStatus)
        this.name = `in`.readString()
        this.createTime = `in`.readLong()
    }

    override fun toString(): String {
        return "BUserInfo{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}'
    }

    companion object {
        val CREATOR: Parcelable.Creator<WUserInfo?> = object : Parcelable.Creator<WUserInfo?> {
            override fun createFromParcel(source: Parcel): WUserInfo? {
                return WUserInfo(source)
            }

            override fun newArray(size: Int): Array<WUserInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}
