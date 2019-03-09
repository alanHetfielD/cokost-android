package com.madukubah.cokost.view.simpan

import android.support.v4.app.Fragment
import android.content.Context
import com.madukubah.cokost.database.DatabaseSimpan
import com.madukubah.cokost.model.Room
import com.madukubah.cokost.model.response.RoomResponse
import com.madukubah.cokost.view.room.adapter.ListRoomRecylerAdapter

open class SimpanPresenter
(
        private val view : SimpanView.View
):
        SimpanView.Presenter
{
    override fun loadData(roomResponse: RoomResponse) {
        if( roomResponse.Rooms.isEmpty() ){
            dataSize--
            complete(listRoom.size)
            return
        }

        listRoom.addAll( roomResponse.Rooms )

        complete(listRoom.size)

        adapter.notifyDataSetChanged()
    }


    override fun loadFromDatabase() {
        view.empty( false )
        view.showLoading()
        listRoom.clear()
        ids.clear()
        ids = databaseSimpan.getAll() as MutableList<Long>
        if( ids .isEmpty() )
        {
            adapter.notifyDataSetChanged()
            view.hideLoading()
            view.empty( true )
        }
        dataSize = ids.size
    }

    override fun complete(size: Int) {
        if( dataSize == size )
        {
            view.hideLoading()
        }
    }

    override fun start() {
        context = checkNotNull(  (view as Fragment).context )
        databaseSimpan = DatabaseSimpan( context  )
        adapter = ListRoomRecylerAdapter( context , listRoom )
    }

    internal lateinit var context : Context
    internal lateinit var databaseSimpan : DatabaseSimpan
    internal lateinit var adapter: ListRoomRecylerAdapter
    internal var listRoom: MutableList<Room> = mutableListOf()
    internal var ids: MutableList<Long> = mutableListOf()
    private var dataSize = 0
}