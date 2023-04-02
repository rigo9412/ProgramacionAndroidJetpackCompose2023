package com.tec.pokedexapp.ui.pokemon

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.pokedexapp.data.PokemonLocalRepository
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.data.source.PokemonLocalAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class PokemonViewModel(
    private val pokemonLocalRepository: PokemonLocalRepository
) : ViewModel() {

    private val _pokedexState = MutableStateFlow<PokemonModelState>(PokemonModelState())
    var pokedexState: StateFlow<PokemonModelState> = _pokedexState

    init{
        viewModelScope.launch {
            pokemonLocalRepository.getPokemons().collect { pokemons ->
                _pokedexState.value = pokedexState.value.copy(fullPokemon = pokemons, unknownPokemon = pokemons)
            }
        }
    }

    fun addViewedPokemon(id: Int){
        updatePokemon(id)
        _pokedexState.value.copy(viewedPokemon = _pokedexState.value.viewedPokemon + _pokedexState.value.fullPokemon[id - 1])
        _pokedexState.value.copy(unknownPokemon = _pokedexState.value.unknownPokemon - _pokedexState.value.fullPokemon[id - 1])
    }

    fun updatePokemon(id: Int){
        val pokemonList = _pokedexState.value.fullPokemon.toMutableList()
        pokemonList[id - 1] = pokemonList[id-1].copy(discovered = true)
        _pokedexState.value.fullPokemon = pokemonList
    }

    fun getPokemonCount(viewed: Boolean = false, type: String = ""): Int {
        var countList =
            if (viewed) _pokedexState.value.viewedPokemon else _pokedexState.value.unknownPokemon
        return if (type != "") countList.count { it.type1 == type || it.type2 == type } else countList.count()
    }

    fun getLegendaryPokemonCount(): Int{
        return _pokedexState.value.viewedPokemon.count(){ it.legendary }
    }

    fun completedPokedex(): Boolean{
        return _pokedexState.value.viewedPokemon.count() == _pokedexState.value.fullPokemon.count()
    }

    fun getRandomUnknownPokemon(): Pokemon?{
        if(_pokedexState.value.unknownPokemon.isEmpty()){
            return null
        }
        return _pokedexState.value.unknownPokemon[Random.nextInt(0,_pokedexState.value.unknownPokemon.size)]
    }

    fun getRandomPokemonList(size: Int): List<Pokemon> {
        return _pokedexState.value.fullPokemon.shuffled().take(size)
    }
}

data class PokemonModelState(
    var fullPokemon: List<Pokemon> = listOf(),
    var viewedPokemon: List<Pokemon> = listOf(),
    var unknownPokemon: List<Pokemon> = listOf()
)