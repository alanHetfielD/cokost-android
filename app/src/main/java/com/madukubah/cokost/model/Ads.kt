package com.madukubah.cokost.model

import com.google.gson.annotations.SerializedName

data class Ads (
    @SerializedName("advertise_id") var advertise_id : Long,
    @SerializedName("advertise_photo") var advertise_photo : String,
    @SerializedName("advertise_desc") var advertise_desc : String
)