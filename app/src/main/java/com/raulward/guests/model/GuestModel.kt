package com.raulward.guests.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Guests")
class GuestModel  {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = false


}