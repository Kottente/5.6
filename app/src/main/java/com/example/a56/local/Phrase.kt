package com.example.a56.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Phrase(
    @PrimaryKey(autoGenerate = true)
    val phraseId:Int,
    val phrase: String

)
