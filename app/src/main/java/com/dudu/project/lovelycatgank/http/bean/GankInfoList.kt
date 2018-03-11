package com.dudu.project.lovelycatgank.http.bean

import android.content.Loader
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by admin on 2018/3/4.
 */
data class GankInfoList(
        var error : Boolean,
        var results: ArrayList<GankInfo>? = null) : Serializable, Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    constructor() : this( false, null)

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeByte((if (error) 1 else 0).toByte())
        dest?.writeList(results)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<GankInfo> = object : Parcelable.Creator<GankInfo> {
            override fun createFromParcel(source: Parcel): GankInfo {
                return GankInfo(source)
            }

            override fun newArray(size: Int): Array<GankInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}


