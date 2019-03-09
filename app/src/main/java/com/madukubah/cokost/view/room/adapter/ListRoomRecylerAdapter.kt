package com.madukubah.cokost.view.room.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.madukubah.cokost.R
import com.madukubah.cokost.model.Room

class ListRoomRecylerAdapter
(private val context: Context, private val data: List<Room>)
    :
        RecyclerView.Adapter<RoomViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return (
                RoomViewHolder
                (
                        LayoutInflater.from(context).inflate(R.layout.card_view_item_detail_kost,parent,false)
                )
                )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bindItem( data[ position ] )
    }
}