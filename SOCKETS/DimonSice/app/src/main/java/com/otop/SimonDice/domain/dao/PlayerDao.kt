package com.otop.SimonDice.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.otop.SimonDice.domain.models.Player

@Dao
interface PlayerDao{
    @Query("SELECT * FROM Player")
    fun getAll(): List<Player>

    @Query("SELECT * FROM Player WHERE id IN (:userIds)")
    fun getAllByIds(userIds: IntArray): List<Player>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(players: List<Player>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(player: Player)

    @Delete
    fun deleteAll(player: List<Player>)

    @Delete
    fun delete(player: Player)
}