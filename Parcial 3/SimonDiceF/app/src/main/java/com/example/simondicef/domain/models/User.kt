package com.example.simondicef.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val name: String = "-",
    val score: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)