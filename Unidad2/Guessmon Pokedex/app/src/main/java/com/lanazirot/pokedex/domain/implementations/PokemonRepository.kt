package com.lanazirot.pokedex.domain.implementations

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lanazirot.pokedex.domain.interfaces.IPokemonRepository
import com.lanazirot.pokedex.domain.models.PokemonMapped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository : IPokemonRepository {
    override suspend fun getPokemonList(): List<PokemonMapped> {
        return withContext(Dispatchers.IO) {
            val json = javaClass.classLoader?.getResource("assets/json/pokemon.json")?.readText()
            val listType = object : TypeToken<List<PokemonMapped>>() {}.type
            Gson().fromJson(json, listType)
        }
    }

    override suspend fun getPokemonById(id: Int): PokemonMapped {
        return withContext(Dispatchers.IO) {
            val json = javaClass.classLoader?.getResource("assets/json/pokemon.json")?.readText()
            val listType = object : TypeToken<List<PokemonMapped>>() {}.type
            val pokemonList = Gson().fromJson(json, listType) as List<PokemonMapped>
            pokemonList.first { it.id == id }
        }
    }
}