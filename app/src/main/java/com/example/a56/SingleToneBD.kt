package com.example.a56

import androidx.room.Room
import androidx.room.RoomDatabase

object SingleToneBD {
    val db = Room.databaseBuilder(
        MainActivity.getContext(),
        AppDatabase::class.java, "squirrel1"
    ).build()
}