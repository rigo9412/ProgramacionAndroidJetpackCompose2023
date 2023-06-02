package com.tec.pokedexapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "users")
data class User (
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val country: String = "",
    val startDate: String = "",
    val finishDate: String = "",
    val topScore1: Int = 0,
    val topScore2: Int = 0,
    val topScore3: Int = 0,
    val triesToFinish: Int = 0,
    val minutesToFinish: Int = 0
)