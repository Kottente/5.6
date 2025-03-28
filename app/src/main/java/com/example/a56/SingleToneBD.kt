package com.example.a56

import androidx.room.Room
import com.example.a56.local.AppDatabase

object SingleToneBD {
    val db = Room.databaseBuilder(
        MainActivity.getContext(),
        AppDatabase::class.java, "squirrel3"
    ).build()
}