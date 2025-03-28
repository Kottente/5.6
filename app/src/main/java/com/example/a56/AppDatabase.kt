package com.example.a56

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Squirrel::class), version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun squirrelDao(): SquirrelDAO
}