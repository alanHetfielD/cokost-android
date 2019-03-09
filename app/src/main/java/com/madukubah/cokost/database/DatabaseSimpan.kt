package com.madukubah.cokost.database

import android.content.Context
import com.madukubah.cokost.model.Room
import org.jetbrains.anko.db.LongParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DatabaseSimpan
(
        val context: Context?
)
{
    fun add( room : Room)
    {
        context?.database?.use {
            insert(TableRoom.TABLE_ROOM,
                    TableRoom.KAMAR_ID
                            to room.kamar_id
            )
        }
    }

    fun remove( room : Room)
    {
        context?.database?.use {
            delete(
                    TableRoom.TABLE_ROOM,
                    "(${TableRoom.KAMAR_ID} = {id})",
                    "id" to room.kamar_id
            )
        }
    }

    fun isExist( room : Room ) : Boolean
    {
        var found = false
        context?.database?.use {
            val e = select(
                    TableRoom.TABLE_ROOM
            ).whereArgs(
            "(${TableRoom.KAMAR_ID} = {id})",
                    "id" to room.kamar_id
            ).parseOpt(LongParser)

            if( e != null )
            {
                found = true
            }
        }
        return found
    }

    fun getAll() : List<Long>
    {
        lateinit var data : List<Long>
        context?.database?.use {
            data = select(
                    TableRoom.TABLE_ROOM
            ).parseList(LongParser)
        }

        return data
    }
}