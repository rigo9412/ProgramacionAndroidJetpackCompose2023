package com.otop.poketest.data.source

import android.content.res.AssetManager
import com.otop.poketest.data.Pokemon
import com.otop.poketest.data.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class PokemonLocalRepository(
    private val manager: AssetManager,
    private val pokemonLocalAPI: PokemonLocalAPI = PokemonLocalAPI()) {

    fun getPokemons() : Flow<List<Pokemon>> {
        return flowOf(pokemonLocalAPI.getPokemons(manager).map {it.asExternalModel()})
    }
}