package com.otop.poketest.data.source

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.otop.poketest.data.PokemonEntity
import java.io.IOException

class PokemonLocalAPI: IPokemonsLocalAPI {
    override fun getPokemons(manager: AssetManager): List<PokemonEntity> {
        lateinit var jsonString: String

        try {
            jsonString = manager.open("pokemons.json").bufferedReader().use { it.readText() }
        }
        catch (ioException: IOException) {
            println(ioException.message)
        }

        val listCountryType = object : TypeToken<List<PokemonEntity>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)
    }
}