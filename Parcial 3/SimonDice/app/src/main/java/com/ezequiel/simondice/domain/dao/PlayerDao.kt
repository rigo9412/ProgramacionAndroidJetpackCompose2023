package com.ezequiel.simondice.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ezequiel.simondice.domain.modelo.Player

@Dao
interface PlayerDao {
    @Query("SELECT * FROM Player")
    fun getAll(): List<Player>

    @Query("SELECT * FROM Player WHERE id IN ('userId')")
    fun getAllByIds(userIds: IntArray): List<Player>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(players:List<Player>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(player: Player)

    @Delete
    fun deleteAll(players: List<Player>)

    @Delete
    fun delete(player: Player)
}