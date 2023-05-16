package com.otop.SimonDice.domain.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otop.SimonDice.domain.models.Player

@Database(
    entities = [Player::class],
    version = 1
)

abstract class SimonDB : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}