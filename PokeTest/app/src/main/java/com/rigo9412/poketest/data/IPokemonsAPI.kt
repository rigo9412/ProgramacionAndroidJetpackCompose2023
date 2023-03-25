package com.rigo9412.poketest.data

import android.content.Context

interface IPokemonsLocalAPI {
    fun getPokemons(context: Context) : List<PokemonEntity>
//    fun getPokemonByNumber(number: Int) : PokemonEntity
//    fun getPokemonEvolutions(number: Int) : List<PokemonEntity>
//    fun getPokemonByName(name: String) : List<PokemonEntity>
}