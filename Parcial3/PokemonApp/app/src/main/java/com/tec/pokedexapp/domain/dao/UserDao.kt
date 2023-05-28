package com.tec.pokedexapp.domain.dao

import androidx.room.*
import com.tec.pokedexapp.data.model.PokemonEntity
import com.tec.pokedexapp.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}
