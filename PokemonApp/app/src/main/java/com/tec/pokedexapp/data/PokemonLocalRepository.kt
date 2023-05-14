package com.tec.pokedexapp.data

import android.content.res.AssetManager
import android.util.Log
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.data.model.toEntity
import com.tec.pokedexapp.data.model.toModel
import com.tec.pokedexapp.data.source.PokemonLocalAPI
import com.tec.pokedexapp.domain.dao.PokemonDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PokemonLocalRepository(
    private val assetManager: AssetManager,
    private val pokemonAPI: PokemonLocalAPI = PokemonLocalAPI(),
    private val pokemonDao: PokemonDao
){
    fun getPokemons(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemons().map { entities ->
            entities.map { entity -> entity.toModel() }
        }
    }

    suspend fun insertAll(pokemons: Flow<List<Pokemon>>) {
        pokemons.collect { pokemonList ->
            pokemonDao.insertAll(pokemonList.map { it.toEntity() })
        }
        Log.d("INSERT","Inserted")
    }

    suspend fun updateDiscoveredPokemon(id: Int) {
        pokemonDao.updateDiscoveredPokemon(id)
        Log.d("UPDATE","$id")
    }

    fun initialize(): Flow<List<Pokemon>> {
        return flowOf(pokemonAPI.getPokemons(assetManager))
    }
}