package com.rigo9412.poketest.data

import android.content.Context
import android.content.res.AssetManager
import com.rigo9412.poketest.data.model.Pokemon
import com.rigo9412.poketest.data.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonLocalRepository(
    private val  manager: AssetManager,
    private val pokemonLocalAPI: PokemonLocalAPI = PokemonLocalAPI()) {





    fun getPokemons() : Flow<List<Pokemon>> {
        return flowOf(pokemonLocalAPI.getPokemons(manager).map { it.asExternalModel() })
    }
}