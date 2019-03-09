package com.madukubah.cokost.view.simpan

import com.madukubah.cokost.api.CokostApi
import com.madukubah.cokost.config.base.Config
import com.madukubah.cokost.model.response.RoomResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SimpanPresenterAnko
(
        private val view : SimpanView.View
)
    :
        SimpanPresenter( view )
{
    override fun loadFromDatabase() {
        super.loadFromDatabase()
        for (i in 0..ids.size-1){
            val id = ids[i]
            val req = CokostApi.getRoomById( ""+id  )
            async(UI){
                val data = bg {
                    Config.gson.fromJson(
                            CokostApi.doRequest( req ),
                            RoomResponse::class.java
                    )
                }
                data.await()
                loadData( data.getCompleted() )
            }
        }
    }
}