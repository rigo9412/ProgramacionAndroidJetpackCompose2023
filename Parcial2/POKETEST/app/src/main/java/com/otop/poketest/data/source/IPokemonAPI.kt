package com.otop.poketest.data.source

import android.content.res.AssetManager
import com.otop.poketest.data.PokemonEntity

interface IPokemonsLocalAPI {
    fun getPokemons (manager: AssetManager)  : List<PokemonEntity>
}