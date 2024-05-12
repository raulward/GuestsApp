package com.raulward.guests.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.raulward.guests.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guests WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM Guests")
    fun getAllGuests(): List<GuestModel>

    @Query("SELECT * FROM Guests WHERE presence = 1")
    fun getAllPresents(): List<GuestModel>

    @Query("SELECT * FROM Guests WHERE presence = 0")
    fun getAllAbsents(): List<GuestModel>

}