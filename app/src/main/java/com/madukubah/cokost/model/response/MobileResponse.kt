package com.madukubah.cokost.model.response

import com.google.gson.annotations.SerializedName
import com.madukubah.cokost.model.Mobile

data class MobileResponse (
        @field:SerializedName("value")
        val Mobiles : List<Mobile>
)