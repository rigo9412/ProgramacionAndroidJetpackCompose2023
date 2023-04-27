package com.rigo9412.poketest.data

import android.content.Context
import android.content.res.AssetManager
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rigo9412.poketest.data.model.PokemonEntity
import java.io.IOException


class PokedexCallback(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val pokedex = initPokedex(context.assets)
        val dao = AppDatabase.getDatabase(context)

        dao.pokemonDao().addMultiplePokemons(pokedex)



    }

    fun initPokedex(manager: AssetManager): List<PokemonEntity> {
        lateinit var jsonString: String
        try {
            jsonString = manager.open("pokemons.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            println(ioException.message)
        }

        val listPokemon = object : TypeToken<List<PokemonEntity>>() {}.type
        return Gson().fromJson(jsonString, listPokemon)
    }
}