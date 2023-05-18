package com.example.simondice.domain.models

import androidx.room.Entity

@Entity(tableName = "Top")
data class Player(var id :Int? = 0,var name: String = "???",var score: Int = 0, var level: Int = 0)