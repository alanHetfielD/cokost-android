package com.madukubah.cokost.model

import com.google.gson.annotations.SerializedName

data class Mobile (
        @SerializedName("mobile_id") var mobile_id : Long,
        @SerializedName("mobile_version") var mobile_version : String
)