package com.tec.pokedexapp.domain.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tec.pokedexapp.data.model.PokemonEntity
import com.tec.pokedexapp.data.model.User

@Database(entities = [PokemonEntity::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}