package com.madukubah.cokost.view.kost

import android.content.Context
import com.madukubah.cokost.model.Kost
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.view.kost.adapter.ListKostRecylerAdapter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import android.support.v4.app.Fragment
import com.madukubah.cokost.api.CokostApi
import com.madukubah.cokost.config.base.Config
import com.madukubah.cokost.model.response.AdsResponse
import com.madukubah.cokost.model.response.RoomResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

open class ListPresenter
    ( private val  view : KostView.ListView )
    :
        KostView.ListPresenter,
        AnkoLogger
{
    override fun loadMore(response: KostResponse) {
        view.hideLoading()
        if( response.Kosts.size <= 0 )
        {
            view.emptyMore( true )
            return
        }
        listKost.addAll( response.Kosts )
        adapter.notifyDataSetChanged()

        view.empty(false)
        view.emptyMore( false)
    }

    fun getAds()
    {
        val req = CokostApi.getAds()
        info("req = " + req )
        async(UI)
        {
            val data = bg {
                Config.gson.fromJson(
                        CokostApi.doRequest( req ),
                        AdsResponse::class.java
                )
            }
            data.await()
            view.loadAdvertisement( data.getCompleted() )
        }
    }
    override fun loadKost(response: KostResponse) {
        view.hideLoading()
        info { "data kost + " + response }
        listKost.clear()

        if( response.Kosts.size <= 0 )
        {
            view.empty(true)
            view.emptyMore( true)
            adapter.notifyDataSetChanged()
            return
        }
        listKost.addAll( response.Kosts )
        adapter.notifyDataSetChanged()

        view.empty(false)
        view.emptyMore( false)
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData() {
        listKost.clear()
        adapter.notifyDataSetChanged()

        view.empty(false)
        view.emptyMore( true)
        view.showLoading()

    }

    override fun start() {
        context = checkNotNull((view as Fragment).context)
        adapter = ListKostRecylerAdapter(context,listKost)
        getAds()
    }

    internal val listKost : MutableList<Kost> = mutableListOf()
    internal lateinit var adapter : ListKostRecylerAdapter
    internal lateinit var context : Context
}