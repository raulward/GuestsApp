package com.raulward.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raulward.guests.model.GuestModel
import com.raulward.guests.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = "Guest inserted!"
            } else {
                _saveGuest.value = "Fail in guest insertion."
            }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Guest updated!"
            } else {
                _saveGuest.value = "Fail in guest update."
            }
        }

    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

}