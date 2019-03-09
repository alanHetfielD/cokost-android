package com.madukubah.cokost.model.response

import com.google.gson.annotations.SerializedName
import com.madukubah.cokost.model.Kost

data class KostResponse (
        @field:SerializedName("value")
        val Kosts : List<Kost>
)