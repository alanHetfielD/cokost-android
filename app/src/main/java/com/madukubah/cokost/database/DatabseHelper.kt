package com.madukubah.cokost.database


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper
(
        ctx: Context
):
        ManagedSQLiteOpenHelper
        (
                ctx,
                "cokost.db",
                null,
                2
        )
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(TableRoom.TABLE_ROOM,
                true,
                TableRoom.KAMAR_ID to INTEGER + PRIMARY_KEY
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, NewVersion: Int) {
        db?.dropTable( TableRoom.TABLE_ROOM , true )
    }

    companion object {
        private var instance : DatabaseHelper? = null

        @Synchronized
        fun getInstance( ctx: Context): DatabaseHelper
        {
            if(instance == null)
            {
                instance = DatabaseHelper( ctx.applicationContext )
            }

            return instance as DatabaseHelper
        }
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)

