package com.rigo9412.poketest.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class PokemonLocalAPI: IPokemonsLocalAPI {
    override fun getPokemons(context: Context): List<PokemonEntity> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("pokemons.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            println(ioException.message)
        }

        val listCountryType = object : TypeToken<List<PokemonEntity>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)


    }


}