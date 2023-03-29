package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.lanazirot.pokedex.domain.models.Pokemon

@Composable
fun PokedexList(pokemons: List<Pokemon>) {
    LazyColumn {
        items(pokemons) {
            p -> PokemonSimpleCard(pokemon = p)
        }
    }
}
