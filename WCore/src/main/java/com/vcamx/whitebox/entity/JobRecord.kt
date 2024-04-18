package com.vcamx.whitebox.entity

import android.app.job.JobInfo
import android.app.job.JobService
import android.content.pm.ServiceInfo
import android.os.Parcel
import android.os.Parcelable

class JobRecord(
    var mJobInfo: JobInfo? = null,
    var mServiceInfo: ServiceInfo? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(JobInfo::class.java.classLoader),
        parcel.readParcelable(ServiceInfo::class.java.classLoader)
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(mJobInfo, flags)
        dest.writeParcelable(mServiceInfo, flags)
    }

    companion object CREATOR : Parcelable.Creator<JobRecord> {
        override fun createFromParcel(parcel: Parcel): JobRecord {
            return JobRecord(parcel)
        }

        override fun newArray(size: Int): Array<JobRecord?> {
            return arrayOfNulls(size)
        }
    }
}
