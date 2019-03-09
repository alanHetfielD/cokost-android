package com.madukubah.cokost.view.kost

import android.support.v4.app.Fragment
import com.madukubah.cokost.config.base.BasePresenter
import com.madukubah.cokost.config.base.BaseView
import com.madukubah.cokost.config.base.InfiniteScrollListener
import com.madukubah.cokost.model.response.AdsResponse
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.model.response.MobileResponse
import com.madukubah.cokost.model.response.RoomResponse

interface KostView {
//    MAIN
    interface MainPresenter
        : BasePresenter
    {
        fun BottomNavMenuSelected(id : Int)
    }
    interface MainView
        : BaseView< MainPresenter >
    {
        fun selectFragment( fragment : Fragment )
        fun loadMobileVersion(response : MobileResponse)

    }

    interface ListPresenter
        : BasePresenter
    {
        fun onResume()
        fun loadData()
        fun loadKost(response : KostResponse)
        fun loadMore(response : KostResponse)
    }
    interface  ListView
        : BaseView< ListPresenter >
    {
        fun empty(bool : Boolean)
        fun showLoading()
        fun hideLoading()
        fun emptyMore( bool : Boolean )
        fun loadAdvertisement(response : AdsResponse)

    }


}