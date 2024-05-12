package com.raulward.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.raulward.guests.viewmodel.GuestFormViewModel
import com.raulward.guests.R
import com.raulward.guests.constants.DataBaseConstants
import com.raulward.guests.databinding.ActivityGuestFormBinding
import com.raulward.guests.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioPresent.isChecked = true

        binding.buttonSave.setOnClickListener(this)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        observe()
        loadData()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editGuestName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }
            viewModel.save(model)


        }
    }

    private fun observe () {
        viewModel.guest.observe(this) {
            binding.editGuestName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this) {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.DB.GUEST_ID)
            viewModel.get(guestId)
        }
    }
}