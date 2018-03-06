package com.dudu.project.lovelycatgank.http.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.*

/**
 * Created by admin on 2018/3/4.
 */
data class GankInfo(
                var _id: String ?="",
                var desc: String?="",
                var createdTime: Date,
//                var images:String ?="",
//                var source: String ?= "",
                var publishedTime: Date,
                var type: String?="",
                var url: String?="",
                var used: Boolean = false,
                var who: String? = "") : Serializable, Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readSerializable() as Date,
//            source.readString(),
//            source.readString(),
            source.readSerializable() as Date,
            source.readString(),
            source.readString(),
            1.toByte().equals(source.readByte()),
            source.readString())

    override fun describeContents(): Int {
        return 0
    }

    constructor() : this("", "", Date(), Date(), "", "", false, "")

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(_id)
        dest?.writeString(desc)
        dest?.writeSerializable(createdTime)
//        dest?.writeString(images)
//        dest?.writeString(source)
        dest?.writeSerializable(publishedTime)
        dest?.writeString(type)
        dest?.writeString(url)
        dest?.writeByte((if (used) 1 else 0).toByte())
        dest?.writeString(who)
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


