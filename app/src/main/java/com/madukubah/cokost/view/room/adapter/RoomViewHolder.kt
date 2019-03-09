package com.madukubah.cokost.view.room.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.madukubah.cokost.BuildConfig
import com.madukubah.cokost.R
import com.madukubah.cokost.config.base.Config
import com.madukubah.cokost.model.Room
import com.madukubah.cokost.view.activity.detail_room.DetailRoomActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class RoomViewHolder
( view : View)
    :
        RecyclerView.ViewHolder(view)
{
    private val img_item_photo : ImageView = view.find( R.id.img_item_photo )
    private val tv_item_room_name : TextView = view.find( R.id.tv_item_room_name )
    private val tv_item_room_price : TextView = view.find( R.id.tv_item_room_price )
    private val tv_item_room_facilities : TextView = view.find( R.id.tv_item_room_facilities )
    private val btn_detail_room : Button = view.find( R.id.btn_detail_room )

    fun bindItem( roomItem : Room )
    {
        val images = roomItem.kamar_foto.split(";")
        Glide.with(itemView)
                .load(BuildConfig.KAMAR_IMAGE+ images[0])
                .into(img_item_photo)
        tv_item_room_name.text = "Tipe : " + roomItem.kamar_type.capitalize()
        tv_item_room_price.text = "Rp. "+ Config.priceFormat( roomItem.kamar_harga ) +" /Tahun "
        tv_item_room_facilities.text = roomItem.kamar_facility

        val ctx = itemView.context
        btn_detail_room.onClick {
            ctx.startActivity<DetailRoomActivity>(
                    DetailRoomActivity.ROOM to roomItem
            )
        }
    }
}