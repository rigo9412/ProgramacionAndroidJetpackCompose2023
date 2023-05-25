package com.example.simondice.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Player")
data class Player(
    @PrimaryKey
    var id :Int? = 0,
    var name: String = "???",
    var score: Int = 0,
    var level: Int = 0)