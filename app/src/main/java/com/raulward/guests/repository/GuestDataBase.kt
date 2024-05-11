package com.raulward.guests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.raulward.guests.constants.DataBaseConstants

class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Cria a tabela Guest no banco de dados
        db.execSQL(
            "CREATE TABLE" + DataBaseConstants.DB.TABLE_NAME + " (" +
                    DataBaseConstants.DB.COLUMNS.ID + " integer primary key autoincrement, " +
                    DataBaseConstants.DB.COLUMNS.NAME + " text, " +
                    DataBaseConstants.DB.COLUMNS.NAME + " integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}