package com.ezequiel.simondice.domain.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey()
    var id :Int? = 0,
    var name: String = "???",
    var score: Int = 0,
    var level: Int = 0) {

}