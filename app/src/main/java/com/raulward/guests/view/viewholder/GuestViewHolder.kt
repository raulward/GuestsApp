package com.raulward.guests.view.viewholder


import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.raulward.guests.databinding.RowGuestBinding
import com.raulward.guests.model.GuestModel
import com.raulward.guests.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textGuest.text = guest.name

        bind.textGuest.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textGuest.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Removing Guest")
                .setMessage("Are you sure you want to remove the guest?")
                .setPositiveButton(
                    "YES"
                ) { dialog, which -> listener.onDelete(guest.id) }
                .setNegativeButton("NO", null)
                .create()
                .show()


            true
        }

    }

}