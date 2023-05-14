package com.tec.pokedexapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "users")
data class User (
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String,
    val startDate: LocalDate,
    val finishDate: LocalDate,
    val topScores: List<Int>,
    val triesToFinish: Int,
    val minutesToFinish: Int
)