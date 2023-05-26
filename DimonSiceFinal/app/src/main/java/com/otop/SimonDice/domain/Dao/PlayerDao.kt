package com.otop.SimonDice.domain.Dao

import android.app.Person
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.otop.SimonDice.domain.models.Player

@Dao
interface PlayerDao {
    @Query("SELECT * from top")
    fun getAll(): List<Player>

    @Query("SELECT * FROM top WHERE id IN (:userIds)")
    fun getAllByIds(userIds: IntArray): List<Player>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun  insertAll(players: List<Player>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(player: Player)

    @Delete
    fun deleteAll(player: List<Player>)

    @Delete
    fun delete(player: Player)
}