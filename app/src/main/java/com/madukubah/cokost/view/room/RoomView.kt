package com.madukubah.cokost.view.room

import com.madukubah.cokost.config.base.BasePresenter
import com.madukubah.cokost.config.base.BaseView
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.model.response.RoomResponse

interface RoomView {
    interface RoomPresenter
        : BasePresenter
    {
        fun onResume()
        fun loadData(  )
        fun loadRoom(response : RoomResponse)
        fun loadkost(response : KostResponse)
        fun loadMore(response : RoomResponse)
    }
    interface  RoomView
        : BaseView<RoomPresenter>
    {
        fun empty(bool : Boolean, length : Int)
        fun emptyMore( empty : Boolean )
        fun onLoadKost( response: KostResponse )
        fun showLoading()
        fun hideLoading()
    }

}