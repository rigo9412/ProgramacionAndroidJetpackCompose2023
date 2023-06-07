package com.program.nivelacion.ui.data

import androidx.room.*

@Dao
interface ResultadoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addResult(resultados: Resultados)

    @Query("SELECT * FROM Resultados")
    fun getAllResults(): List<Resultados>
}