package com.example.nivelacion

import androidx.room.*

@Dao
interface InformacionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addResult(informacion: Informacion)

    @Query("SELECT * FROM Informacion")
    fun getAllResults(): List<Informacion>
}