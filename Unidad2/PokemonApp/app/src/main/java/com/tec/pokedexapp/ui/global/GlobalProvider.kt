package com.tec.pokedexapp.ui.global

import android.content.res.AssetManager
import androidx.navigation.NavHostController
import com.tec.pokedexapp.ui.game.GameViewModel
import com.tec.pokedexapp.ui.navigator.main.PerfilViewModel
import com.tec.pokedexapp.ui.pokemon.PokemonViewModel

data class GlobalProvider(
    val gameVM : GameViewModel,
    val pokemonVM : PokemonViewModel,
    val perfilVM : PerfilViewModel,
    val nav : NavHostController,
    val assetManager: AssetManager
)