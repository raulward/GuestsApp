package com.raulward.guests.repository

import android.content.Context
import com.raulward.guests.model.GuestModel

class GuestRepository (context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun insert(guest: GuestModel): Boolean {
        return guestDataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
    }

    fun delete(id: Int){
        val guest = get(id)
        guestDataBase.delete(guest)
    }

    fun getAllGuests(): List<GuestModel> {
        return guestDataBase.getAllGuests()
    }

    fun getAllPresents(): List<GuestModel> {
        return guestDataBase.getAllPresents()
    }

    fun getAllAbsents(): List<GuestModel> {
        return guestDataBase.getAllAbsents()
    }

    fun get(id: Int): GuestModel {
        return guestDataBase.get(id)
    }

}