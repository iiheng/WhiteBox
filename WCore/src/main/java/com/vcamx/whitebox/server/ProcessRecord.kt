package com.vcamx.whitebox.server

import android.os.Binder
import android.os.Parcel
import android.os.Parcelable
import android.os.Process

class ProcessRecord() : Binder(), Parcelable {

    var vuid: Int = 0
    var pid: Int = 0

    fun kill() {
        if (pid > 0) {
//            VActivityManagerService.get().beforeProcessKilled(this);
            try {
                Process.killProcess(pid)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProcessRecord> {
        override fun createFromParcel(parcel: Parcel): ProcessRecord {
            return ProcessRecord(parcel)
        }

        override fun newArray(size: Int): Array<ProcessRecord?> {
            return arrayOfNulls(size)
        }
    }
}