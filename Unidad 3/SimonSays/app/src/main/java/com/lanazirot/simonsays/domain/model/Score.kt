package com.lanazirot.simonsays.domain.model

import androidx.room.Entity

@Entity
data class Score(
    var score :Int = 0,
    var name : String = ""
)