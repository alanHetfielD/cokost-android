package com.madukubah.cokost.view.room.detail

import com.madukubah.cokost.config.base.BasePresenter
import com.madukubah.cokost.config.base.BaseView
import com.madukubah.cokost.model.Room

interface RoomDetailView {
    interface Presenter
        : BasePresenter
    {
        fun favorite()
        fun onSimpanButtonPressed()
    }

    interface View
        : BaseView<Presenter>
    {
        fun updateFavoriteLayout( isFavorite : Boolean )
        fun loadData( room : Room)
        fun showMessage(message : String)
    }

}