package com.madukubah.cokost.view.room.detail

import android.app.Activity
import android.content.Context
import com.madukubah.cokost.api.CokostApi
import com.madukubah.cokost.config.base.Config
import com.madukubah.cokost.database.DatabaseSimpan
import com.madukubah.cokost.model.Room
import com.madukubah.cokost.model.response.RoomResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.info

class RoomDetailPresenter
(
        val room : Room,
        val view : RoomDetailView.View

)
    :
        RoomDetailView.Presenter,
        AnkoLogger
{
    override fun favorite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSimpanButtonPressed() {
        if( isFavorite )
        {
            databaseSimpan.remove( room )
            view.showMessage("Data dibuang")
        }else{
            databaseSimpan.add( room )
            view.showMessage( "Data disimpan" )
        }
        isFavorite = !isFavorite
        view.updateFavoriteLayout( isFavorite  )

    }

    override fun start() {
        isFavorite = databaseSimpan.isExist( room )
        view.updateFavoriteLayout( isFavorite  )
        view.loadData( room )
        hitRoom()
    }

    fun hitRoom()
    {
        val req = CokostApi.hitRoomByRoomid( ""+ room.kamar_id )
        info { "req = " + req}
        async(UI)
        {
            val data = bg {
                Config.gson.fromJson(
                        CokostApi.doRequest( req ),
                        RoomResponse::class.java
                )
            }
            data.await()
        }
    }

    private var context : Context = (view as Activity).applicationContext
    private var databaseSimpan : DatabaseSimpan = DatabaseSimpan(context )
    private var isFavorite = false
}