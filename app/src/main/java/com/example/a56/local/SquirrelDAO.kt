package com.example.a56.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface SquirrelDAO {
    @Query("SELECT * FROM Squirrel")
    fun getAllOfSquirrel(): List<Squirrel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSquirrel(squirrel: Squirrel): Unit

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateSquirrel(squirrel: Squirrel): Unit

    @Delete
    fun deleteSquirrel(squirrel: Squirrel): Unit

    @Insert
    fun insertPhrase(phrase: Phrase): Unit
    @Query("SELECT * FROM Phrase")
    fun getAllPhrase():List<Phrase>
    @Transaction
    @Query("SELECT * FROM Squirrel")
    fun getSquirrelWithPhrase(): List<SquirrelWithPhrase>
}