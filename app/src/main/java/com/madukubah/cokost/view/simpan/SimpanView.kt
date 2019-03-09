package com.madukubah.cokost.view.simpan

import com.madukubah.cokost.config.base.BasePresenter
import com.madukubah.cokost.config.base.BaseView
import com.madukubah.cokost.model.Room
import com.madukubah.cokost.model.response.RoomResponse

interface SimpanView {
    interface Presenter
        : BasePresenter
    {
        fun loadData( roomResponse : RoomResponse)
        fun loadFromDatabase()
        fun complete( size : Int )
    }

    interface View
        : BaseView<Presenter>
    {

        fun showLoading()
        fun hideLoading()
        fun empty( bool : Boolean )
    }
}