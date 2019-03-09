package com.madukubah.cokost.model.response

import com.google.gson.annotations.SerializedName
import com.madukubah.cokost.model.Room

data class RoomResponse (
    @field:SerializedName("value")
    val Rooms : List<Room>
)