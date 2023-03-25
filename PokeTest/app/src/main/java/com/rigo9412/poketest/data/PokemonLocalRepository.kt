package com.rigo9412.poketest.data

import android.content.Context

class PokemonLocalRepository(val pokemonLocalAPI: PokemonLocalAPI) {

    fun getPokemons(context: Context) : List<PokemonEntity>{
        return pokemonLocalAPI.getPokemons(context)
    }
}