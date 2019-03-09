package com.madukubah.cokost.view.kost.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.madukubah.cokost.BuildConfig
import com.madukubah.cokost.R
import com.madukubah.cokost.model.Kost
import com.madukubah.cokost.view.activity.detail_kost.DetailKostActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class KostViewHolder
( view : View)
    :
        RecyclerView.ViewHolder(view)
{
    private val img_item_photo : ImageView = view.find( R.id.img_item_photo )
    private val tv_item_name : TextView = view.find( R.id.tv_item_name )
    private val tv_item_alamat : TextView = view.find( R.id.tv_item_alamat )
    private val tv_item_keterangan : TextView = view.find( R.id.tv_item_keterangan )
//    private val btn_detail : Button = view.find( R.id.btn_detail )

    fun bindItem( kostItem : Kost )
    {
        Glide.with(itemView)
                .load(BuildConfig.KOST_IMAGE+ kostItem.kost_photo)
                .into(img_item_photo)
        tv_item_name.text = kostItem.kost_name.capitalize()
        tv_item_alamat.text = kostItem.kost_address.capitalize()
        tv_item_keterangan.text = kostItem.kost_keterangan.capitalize()
        val ctx = itemView.context
        itemView.onClick {
            ctx.startActivity<DetailKostActivity>(
                    DetailKostActivity.KOST to kostItem.kost_id
            )
        }

    }
}