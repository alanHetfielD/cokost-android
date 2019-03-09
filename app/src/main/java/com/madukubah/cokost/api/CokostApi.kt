package com.madukubah.cokost.api

import com.madukubah.cokost.BuildConfig
import java.net.URL

object CokostApi {
    private const val allKost :String = "kost/kost/?start="
    private const val ByKostId :String = "kost/kost/"
    private const val RoomByKostId :String = "room/kost_id/"
    private const val searchKost :String = "kost/search/"
    private const val RoomAvailable :String = "room/available/0/?start="
    private const val RoomDetail :String = "room/detail/"
    private const val MobileADS :String = "ads/"
    private const val hitRoom:String = "room/hit/"
    fun getKost( start : Int) : String
    {
        return BuildConfig.END_POINT + allKost + ""+start
    }

    fun getKostById( kostId : String  ) : String
    {
        return BuildConfig.END_POINT + ByKostId + kostId
    }

    fun getRoomByKostId( kostId : String ) : String
    {
        return BuildConfig.END_POINT + RoomByKostId + kostId
    }

    fun getRoomAvailable( start : Int ) : String
    {
        return BuildConfig.END_POINT + RoomAvailable + ""+start
    }

    fun getRoomById( roomId : String  ) : String
    {
        return BuildConfig.END_POINT + RoomDetail + roomId
    }

    fun searchKost( query : String  ) : String
    {
        return BuildConfig.END_POINT + searchKost + query
    }

    fun getMobileVersion( ) : String
    {
        return BuildConfig.END_POINT + MobileADS + "get_mobile_version"
    }

    fun getAds( ) : String
    {
        return BuildConfig.END_POINT + MobileADS + "get_ads"
    }

    fun hitRoomByRoomid( roomid: String ) : String
    {
        return BuildConfig.END_POINT + hitRoom + roomid
    }

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}