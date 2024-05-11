package com.raulward.guests.repository

import android.content.ContentValues
import android.content.Context
import com.raulward.guests.constants.DataBaseConstants
import com.raulward.guests.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.DB.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.DB.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.DB.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.DB.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.DB.COLUMNS.PRESENCE, presence)

            val selection = DataBaseConstants.DB.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.DB.TABLE_NAME, values, selection, args)

            true

        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val selection = DataBaseConstants.DB.COLUMNS.ID + " = ?" // Where
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.DB.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAllGuests(): MutableList<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val columnId = DataBaseConstants.DB.COLUMNS.ID
            val columnName = DataBaseConstants.DB.COLUMNS.NAME
            val columnPresence = DataBaseConstants.DB.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )

            val cursor =
                db.query(DataBaseConstants.DB.TABLE_NAME, projection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(columnId))
                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }

        return list
    }

    fun getAllPresents(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val columnId = DataBaseConstants.DB.COLUMNS.ID
            val columnName = DataBaseConstants.DB.COLUMNS.NAME
            val columnPresence = DataBaseConstants.DB.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )

            val selection = columnPresence + " = ?"
            val args = arrayOf(DataBaseConstants.DB.CHECKER.PRESENCE_TRUE)

            val cursor =
                db.query(
                    DataBaseConstants.DB.TABLE_NAME,
                    projection,
                    selection,
                    args,
                    null,
                    null,
                    null
                )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(columnId))
                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }

        return list
    }

    fun getAllAbsents(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val columnId = DataBaseConstants.DB.COLUMNS.ID
            val columnName = DataBaseConstants.DB.COLUMNS.NAME
            val columnPresence = DataBaseConstants.DB.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )

            val selection = columnPresence + " = ?"
            val args = arrayOf(DataBaseConstants.DB.CHECKER.PRESENCE_FALSE)

            val cursor =
                db.query(
                    DataBaseConstants.DB.TABLE_NAME,
                    projection,
                    selection,
                    args,
                    null,
                    null,
                    null
                )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(columnId))
                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }

        return list
    }

    fun get(id: Int): GuestModel? {

        var element: GuestModel? = null

        try {
            val db = guestDataBase.readableDatabase

            val columnId = DataBaseConstants.DB.COLUMNS.ID
            val columnName = DataBaseConstants.DB.COLUMNS.NAME
            val columnPresence = DataBaseConstants.DB.COLUMNS.PRESENCE

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )

            val selection = DataBaseConstants.DB.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor =
                db.query(DataBaseConstants.DB.TABLE_NAME, projection, selection, args, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val name = cursor.getString(cursor.getColumnIndex(columnName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnPresence))

                    element = GuestModel(id, name, presence == 1)
                }
            }
            cursor.close()

        } catch (e: Exception) {
            return element
        }

        return element
    }

}