package com.raulward.guests.constants

class DataBaseConstants private constructor(){
    object DB {

        const val GUEST_ID = "guestid"
        const val TABLE_NAME = "Guests"

        object COLUMNS {
            const val NAME = "name"
            const val PRESENCE = "presence"
            const val ID = "id"
        }

        object CHECKER {
            const val PRESENCE_TRUE = "1"
            const val PRESENCE_FALSE = "0"
        }

    }
}