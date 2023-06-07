package com.almy.gato

import androidx.room.*

@Dao
interface SumaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSuma(suma: suma)

    @Query("SELECT * FROM suma")
    fun getAllSumas(): List<suma>
}