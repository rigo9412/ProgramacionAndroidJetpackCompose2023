package com.rigo9412.poketest.data.source

import android.content.res.AssetManager
import com.rigo9412.poketest.data.model.PokemonEntity

interface IPokemonsLocalAPI {
    fun getPokemons(manager: AssetManager) : List<PokemonEntity>
//    fun getPokemonByNumber(number: Int) : PokemonEntity
//    fun getPokemonEvolutions(number: Int) : List<PokemonEntity>
//    fun getPokemonByName(name: String) : List<PokemonEntity>
}