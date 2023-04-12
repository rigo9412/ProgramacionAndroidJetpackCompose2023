package com.tec.pokedexapp.data.source

import android.content.res.AssetManager
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.data.model.PokemonEntity

interface IPokemonsLocalAPI {
    fun getPokemons(manager: AssetManager) : List<Pokemon>
}