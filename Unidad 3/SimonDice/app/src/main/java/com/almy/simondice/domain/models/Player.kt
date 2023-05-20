package com.almy.simondice.domain.models

import androidx.room.Entity


@Entity
class Player(val id: Int?, val name: String, val score: Int, val level: Int) {



/*abstract class SimonDB : RoomDatabase()
{
    abstract fun playerDao(): PlayerDao
}*/



}