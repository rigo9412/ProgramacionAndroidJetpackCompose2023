package com.rigo9412.poketest.data.dao

import GameEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM GameEntity ORDER BY score DESC")
    fun getTop(): Flow<List<GameEntity>>

    @Insert
    fun addGame(score: GameEntity)

}