package com.rigo9412.poketest.data

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rigo9412.poketest.data.source.IPokemonsLocalAPI
import com.rigo9412.poketest.data.model.PokemonEntity
import java.io.IOException

class PokemonLocalAPI: IPokemonsLocalAPI {


    override fun getPokemons(manager: AssetManager): List<PokemonEntity> {
        lateinit var jsonString: String
        try {
            jsonString = manager.open("pokemons.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            println(ioException.message)
        }

        val listCountryType = object : TypeToken<List<PokemonEntity>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)


    }


}