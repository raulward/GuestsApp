package com.raulward.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raulward.guests.model.GuestModel
import com.raulward.guests.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: GuestRepository = GuestRepository(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
       listAllGuests.value = repository.getAllGuests()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun getPresent() {
        listAllGuests.value = repository.getAllPresents()
    }

    fun getAbsent() {
        listAllGuests.value = repository.getAllAbsents()
    }


}