package com.madukubah.cokost.model.response

import com.google.gson.annotations.SerializedName
import com.madukubah.cokost.model.Ads

data class AdsResponse (
        @field:SerializedName("value")
        val ADs : List<Ads>
)