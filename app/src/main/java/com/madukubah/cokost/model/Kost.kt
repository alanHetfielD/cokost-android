package com.madukubah.cokost.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
//import kotlinx.android.parcel.Parcelize
//
//@Parcelize
data class Kost(
    @SerializedName("kost_id") var kost_id : Long,
    @SerializedName("user_id") var user_id : Long,
    @SerializedName("kost_name") var kost_name : String,
    @SerializedName("kost_address") var kost_address : String,
    @SerializedName("kost_latitude") var kost_latitude : String,
    @SerializedName("kost_langitude") var kost_langitude : String,
    @SerializedName("kost_photo") var kost_photo : String,
    @SerializedName("kost_keterangan") var kost_keterangan : String,
    @SerializedName("kost_status") var kost_status : Int,
    @SerializedName("user_profile_fullname") var user_profile_fullname : String,
    @SerializedName("user_profile_address") var user_profile_address : String,
    @SerializedName("user_profile_image_path") var user_profile_image_path : String,
    @SerializedName("user_profile_email") var user_profile_email : String,
    @SerializedName("user_profile_phone") var user_profile_phone : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(kost_id)
        parcel.writeLong(user_id)
        parcel.writeString(kost_name)
        parcel.writeString(kost_address)
        parcel.writeString(kost_latitude)
        parcel.writeString(kost_langitude)
        parcel.writeString(kost_photo)
        parcel.writeString(kost_keterangan)
        parcel.writeInt(kost_status)
        parcel.writeString(user_profile_fullname)
        parcel.writeString(user_profile_address)
        parcel.writeString(user_profile_image_path)
        parcel.writeString(user_profile_email)
        parcel.writeString(user_profile_phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Kost> {
        override fun createFromParcel(parcel: Parcel): Kost {
            return Kost(parcel)
        }

        override fun newArray(size: Int): Array<Kost?> {
            return arrayOfNulls(size)
        }
    }
}