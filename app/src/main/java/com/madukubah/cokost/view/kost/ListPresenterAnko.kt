package com.madukubah.cokost.view.kost


import com.madukubah.cokost.api.CokostApi
import com.madukubah.cokost.config.base.Config.gson
import com.madukubah.cokost.model.response.KostResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.info

class ListPresenterAnko
(private val  view : KostView.ListView )
    :
        ListPresenter( view ),
        AnkoLogger
{
    fun loadMoreA()
    {
        view.empty(false)
        view.showLoading()
        val req = CokostApi.getKost( listKost.size )
        info("req = " + req )
        async(UI)
        {
            val data = bg {
                gson.fromJson(
                        CokostApi.doRequest( req ),
                        KostResponse::class.java
                )
            }
            data.await()
            loadMore( data.getCompleted() )
        }
    }
    override fun loadData() {
        super.loadData()

        val req = CokostApi.getKost( listKost.size )
        info("req = " + req )
        async(UI)
        {
            val data = bg {
                gson.fromJson(
                    CokostApi.doRequest( req ),
                    KostResponse::class.java
                )
            }
            data.await()
            loadKost( data.getCompleted() )
        }
    }

    fun searchKost( query : String )
    {
        super.loadData()
        val _query = query.replace(" ","%20")
        val req = CokostApi.searchKost( _query )
        info("req = " + req )
        async(UI)
        {
            val data = bg {
                gson.fromJson(
                        CokostApi.doRequest( req ),
                        KostResponse::class.java
                )
            }
            data.await()
            loadKost( data.getCompleted() )
        }
    }
}