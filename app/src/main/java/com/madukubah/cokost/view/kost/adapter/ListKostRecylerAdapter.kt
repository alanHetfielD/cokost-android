package com.madukubah.cokost.view.kost.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.madukubah.cokost.R
import com.madukubah.cokost.model.Kost

class ListKostRecylerAdapter
(private val context: Context, private val data: List<Kost>)
    :
RecyclerView.Adapter<KostViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        return (
            KostViewHolder
            (
                    LayoutInflater.from(context).inflate(R.layout.card_view_kost_item,parent,false)
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        holder.bindItem( data[ position ] )
    }
}