package com.ezequiel.simondice.domain.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity("Player")
data class Player(
    @PrimaryKey
    var id :Int? = 0,
    var name: String = "???",
    var score: Int = 0,
    var level: Int = 0)