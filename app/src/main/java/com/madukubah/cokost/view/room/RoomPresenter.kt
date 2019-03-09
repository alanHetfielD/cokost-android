package com.madukubah.cokost.view.room

import android.content.Context
import com.madukubah.cokost.model.Room
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.model.response.RoomResponse
import com.madukubah.cokost.view.room.adapter.ListRoomRecylerAdapter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

open class RoomPresenter
(
        private val view : RoomView.RoomView,
        private val contx: Context
)
    :
        RoomView.RoomPresenter,
        AnkoLogger
{
    override fun loadMore(response: RoomResponse) {
        view.hideLoading()
        if( response.Rooms.size <= 0 )
        {
            view.emptyMore( true)
            return
        }

        listRoom.addAll( response.Rooms )
        adapter.notifyDataSetChanged()

        view.empty( false, response.Rooms.size  )
        view.emptyMore( false)
    }

    override fun loadkost(response: KostResponse) {
        view.onLoadKost( response )
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData() {
        listRoom.clear()
        view.empty(false, 0)
        view.emptyMore( true)
        view.showLoading()
        adapter.notifyDataSetChanged()
    }


    override fun loadRoom(response: RoomResponse) {
        view.hideLoading()
        info { "data room + " + response }


        if( response.Rooms.size <= 0 )
        {
            view.emptyMore( true)
            view.empty( true, 0)
            return
        }

        listRoom.addAll( response.Rooms )
        adapter.notifyDataSetChanged()

        view.empty( false, response.Rooms.size  )
        view.emptyMore( false)
    }

    override fun start() {
        context = contx
        adapter = ListRoomRecylerAdapter( context , listRoom )
    }

    internal val listRoom: MutableList<Room> = mutableListOf()
    internal lateinit var adapter : ListRoomRecylerAdapter
    internal lateinit var context : Context
}