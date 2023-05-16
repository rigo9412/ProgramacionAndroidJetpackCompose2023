package com.lanazirot.simonsays.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Top")
data class Player(
    @PrimaryKey
    val id: Int? = 0,
    val name: String? = "",
    val score: Int = 0,
    val level: Int? = 0
)