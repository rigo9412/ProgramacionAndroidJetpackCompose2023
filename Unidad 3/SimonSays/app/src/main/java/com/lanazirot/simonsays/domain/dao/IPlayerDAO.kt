package com.lanazirot.simonsays.domain.dao

import androidx.room.*
import com.lanazirot.simonsays.domain.model.Player

@Dao
interface IPlayerDAO{

    @Query("SELECT * FROM Top")
    fun getAll(): List<Player>

    @Query("SELECT * FROM Top WHERE id = :id")
    fun getById(id: Int): Player

    @Query("SELECT * FROM Top WHERE name = :name")
    fun getByName(name: String): Player

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: Player)

    @Delete
    fun delete(player: Player)

    @Update
    fun update(player: Player)

    @Query("DELETE FROM Top")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(players: List<Player>)
}