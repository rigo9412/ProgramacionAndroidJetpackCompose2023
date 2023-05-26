package com.otop.SimonDice.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top")
data class Player(
    @PrimaryKey
    val id: Int?,
    val name: String,
    val score: Int,
    val level: Int) {

}