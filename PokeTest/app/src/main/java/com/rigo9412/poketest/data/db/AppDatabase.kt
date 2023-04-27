package com.rigo9412.poketest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rigo9412.poketest.data.dao.PokemonDao
import com.rigo9412.poketest.data.dao.ScoreDao
import com.rigo9412.poketest.data.dao.ViewDao
import com.rigo9412.poketest.data.model.PokemonEntity
import com.rigo9412.poketest.data.model.ScoreEntity
import com.rigo9412.poketest.data.model.ViewEntity

@Database(entities = [PokemonEntity::class, ScoreEntity::class, ViewEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scoreDao(): ScoreDao
    abstract fun viewDao(): ViewDao
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database_pokemon_game")
                    .addCallback(PokedexCallback(context))
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}