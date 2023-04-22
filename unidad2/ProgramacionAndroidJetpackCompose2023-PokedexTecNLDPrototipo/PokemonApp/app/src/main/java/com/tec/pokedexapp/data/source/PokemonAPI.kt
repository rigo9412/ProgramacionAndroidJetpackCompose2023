package com.tec.pokedexapp.data.source

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonAPI {
    fun getPokemons(assetManager: AssetManager) : List<Pokemon>{
        val inputStream = assetManager.open("pokemon151.json")
        val json = inputStream.bufferedReader().use{it.readText()}
        val gson = Gson()
        val listType = object : TypeToken<List<Pokemon>>(){}.type
        val pokemons = gson.fromJson<List<Pokemon>>(json,listType)
        return pokemons
    }
}