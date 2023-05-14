package com.ezequiel.simondice.domain.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezequiel.simondice.domain.modelo.Player

@Database(entities = [Player::class], version = 1)
abstract class SimonDB : RoomDatabase(){
    abstract fun playerDao(): PlayerDao
}