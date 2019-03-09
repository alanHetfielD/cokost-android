package com.madukubah.cokost.view.room

import android.content.Context
import com.madukubah.cokost.api.CokostApi
import com.madukubah.cokost.config.base.Config
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.model.response.RoomResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.info

class RoomPresenterAnko
(
        private val view : RoomView.RoomView,
        private val contx : Context

)
    :
        RoomPresenter( view  , contx),
        AnkoLogger
{
    override fun loadData()
    {
        super.loadData()
        val req = CokostApi.getRoomAvailable( listRoom.size )
        async(UI)
        {
            val data = bg {
                Config.gson.fromJson(
                        CokostApi.doRequest( req ),
                        RoomResponse::class.java
                )
            }
            data.await()
            loadRoom( data.getCompleted() )
        }
    }

    fun loadMoreA()
    {
        view.empty(false, 0)
        view.showLoading()
        val req = CokostApi.getRoomAvailable( listRoom.size )
        async(UI)
        {
            val data = bg {
                Config.gson.fromJson(
                        CokostApi.doRequest( req ),
                        RoomResponse::class.java
                )
            }
            data.await()
            loadMore( data.getCompleted() )
        }
    }

    fun loadDataByKostId( id : Long )
    {
        super.loadData()

        val req = CokostApi.getRoomByKostId( "" + id  )
        async(UI)
        {
            val data = bg {
                Config.gson.fromJson(
                        CokostApi.doRequest( req ),
                        RoomResponse::class.java
                )
            }
            data.await()
            loadRoom( data.getCompleted() )
        }
    }

    fun loadKostByKostId( id : Long )
    {
        super.loadData()

        val req = CokostApi.getKostById( "" + id  )
        info { req  }
        async(UI)
        {
            val data = bg {
                Config.gson.fromJson(
                        CokostApi.doRequest( req ),
                        KostResponse::class.java
                )
            }
            data.await()
            loadkost( data.getCompleted() )
        }
    }
}