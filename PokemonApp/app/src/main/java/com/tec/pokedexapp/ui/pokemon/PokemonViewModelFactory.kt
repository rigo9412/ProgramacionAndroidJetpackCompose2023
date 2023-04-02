package com.tec.pokedexapp.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tec.pokedexapp.data.PokemonLocalRepository
import com.tec.pokedexapp.ui.game.GameViewModel

class PokemonViewModelFactory(private val repository: PokemonLocalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class GameViewModelFactory(
    private val pokemonViewModel: PokemonViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GameViewModel::class.java) -> {
                GameViewModel(pokemonViewModel)  as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}