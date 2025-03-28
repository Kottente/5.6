package com.example.a56.local

import androidx.room.Embedded
import androidx.room.Relation

data class SquirrelWithPhrase(
    @Embedded
    val squirrel: Squirrel,
    @Relation(
        entityColumn = "phraseId",
        parentColumn = "phraseId"
    )
    val phrase: Phrase
)
