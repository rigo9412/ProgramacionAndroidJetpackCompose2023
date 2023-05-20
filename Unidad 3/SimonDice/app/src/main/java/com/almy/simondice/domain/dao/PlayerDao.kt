package com.almy.simondice.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.almy.simondice.domain.models.Player

@Dao
interface PlayerDao {
    @Query("SELECT * FROM Player")
    fun getAll(): List<Player>

    @Query("SELECT * FROM Player where id in (:userIds)")
    fun getAllByIds(userIds: IntArray): List<Player>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(players: List<Player>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(player:Player)

    @Delete
    fun deleteAll(person: List<Player>)

    @Delete
    fun delete(person: Player)


}