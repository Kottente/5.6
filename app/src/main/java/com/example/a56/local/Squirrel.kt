package com.example.a56.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity//(tableName = "dasisdas")
data class Squirrel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    //@ColumnInfo(name = "bugugaga")
    val colorTrail: String,
    val name: String,
    val phraseId:Int
)
