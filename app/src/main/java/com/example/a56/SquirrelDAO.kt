package com.example.a56

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}