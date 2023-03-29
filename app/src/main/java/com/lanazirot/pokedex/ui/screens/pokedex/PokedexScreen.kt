package com.lanazirot.pokedex.ui.screens.pokedex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lanazirot.pokedex.domain.implementations.PokemonLocalRepository
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokedexList

@Composable
fun PokedexScreen() {
    var allPokemons by remember { mutableStateOf<List<Pokemon>>(listOf()) }

//    LaunchedEffect(Unit) {
//         allPokemons = PokemonLocalRepository().getPokemonList()
//    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Pokedex Screen")

        PokedexList(pokemons = allPokemons)

    }
}