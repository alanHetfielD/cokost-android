package com.madukubah.cokost.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Room (
    @SerializedName("kost_id") val kost_id : Long,
    @SerializedName("user_id") val user_id : Long,
    @SerializedName("kost_name") val kost_name : String,
    @SerializedName("kost_address") val kost_address : String,
    @SerializedName("kost_latitude") val kost_latitude : String,
    @SerializedName("kost_langitude") val kost_langitude : String,
    @SerializedName("kost_photo") val kost_photo : String,
    @SerializedName("kost_keterangan") val kost_keterangan : String,
    @SerializedName("kost_status") val kost_status : Int,
    @SerializedName("user_profile_fullname") val user_profile_fullname : String,
    @SerializedName("user_profile_address") val user_profile_address : String,
    @SerializedName("user_profile_image_path") val user_profile_image_path : String,
    @SerializedName("user_profile_email") val user_profile_email : String,
    @SerializedName("user_profile_phone") val user_profile_phone : String,
//    ROOM
    @SerializedName("kamar_id") val kamar_id : Long,
    @SerializedName("kamar_type") val kamar_type : String,
    @SerializedName("kamar_harga") val kamar_harga : String,
    @SerializedName("kamar_panjang") val kamar_panjang : String,
    @SerializedName("kamar_lebar") val kamar_lebar : String,
    @SerializedName("kamar_facility") val kamar_facility : String,
    @SerializedName("kamar_quantity") val kamar_quantity : String,
    @SerializedName("kamar_foto") val kamar_foto : String,
    @SerializedName("kamar_hit") val kamar_hit : String

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
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
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
        parcel.writeLong(kamar_id)
        parcel.writeString(kamar_type)
        parcel.writeString(kamar_harga)
        parcel.writeString(kamar_panjang)
        parcel.writeString(kamar_lebar)
        parcel.writeString(kamar_facility)
        parcel.writeString(kamar_quantity)
        parcel.writeString(kamar_foto)
        parcel.writeString(kamar_hit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(parcel)
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }
}