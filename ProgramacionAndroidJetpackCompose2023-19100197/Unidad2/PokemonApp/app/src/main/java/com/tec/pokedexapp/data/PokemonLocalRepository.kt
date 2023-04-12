package com.tec.pokedexapp.data

import android.content.res.AssetManager
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.data.model.asExternalModel
import com.tec.pokedexapp.data.source.IPokemonsLocalAPI
import com.tec.pokedexapp.data.source.PokemonLocalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PokemonLocalRepository(
    private val assetManager: AssetManager,
    private val pokemonAPI: PokemonLocalAPI = PokemonLocalAPI()
){
    fun getPokemons(): Flow<List<Pokemon>> {
        return flowOf(pokemonAPI.getPokemons(assetManager))
    }
}