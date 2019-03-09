package com.madukubah.cokost.view.kost

import android.support.v4.app.Fragment
import com.madukubah.cokost.R
import com.madukubah.cokost.api.CokostApi
import com.madukubah.cokost.config.base.Config
import com.madukubah.cokost.model.response.AdsResponse
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.model.response.MobileResponse
import com.madukubah.cokost.view.room.RoomFragment
import com.madukubah.cokost.view.simpan.SimpanFragment
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.info

class KostPresenter
(view : KostView.MainView)
    :
        KostView.MainPresenter,
        AnkoLogger
{
    val view = view
    lateinit var fragments : List<Fragment>


    override fun BottomNavMenuSelected(id: Int) {
        var fragment : Fragment?= null
        when(id){
            R.id.navigation_kost->{
                fragment = fragments[ 0 ]
            }
//                fragment = ListFragment.create()

            R.id.navigation_room->{
                fragment = fragments[ 1 ]
            }
//                fragment = RoomFragment.create()

            R.id.navigation_favorite ->{
                fragment = fragments[ 2 ]
            }
//                fragment = SimpanFragment.create()
        }

        view.selectFragment( fragment as Fragment )
    }

    override fun start() {
        fragments = listOf(
                ListFragment.create(),
                RoomFragment.create(),
                SimpanFragment.create()
        )
        BottomNavMenuSelected( R.id.navigation_kost )
        getMobileVersion()
    }

    fun getMobileVersion()
    {
        val req = CokostApi.getMobileVersion()
        info("req = " + req )
        async(UI)
        {
            val data = bg {
                Config.gson.fromJson(
                        CokostApi.doRequest( req ),
                        MobileResponse::class.java
                )
            }
            data.await()
            view.loadMobileVersion( data.getCompleted() )
        }
    }


}