package com.tec.pokedexapp.ui.pokemon

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.pokedexapp.data.PokemonLocalRepository
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.data.source.PokemonLocalAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.random.Random

class PokemonViewModel(
    private val pokemonLocalRepository: PokemonLocalRepository
) : ViewModel() {

    private val pokemonListStateKey = "pokemonList"

    private val _pokedexState = MutableStateFlow<PokemonModelState>(PokemonModelState())
    var pokedexState: StateFlow<PokemonModelState> = _pokedexState

    init {
        Log.d("INIT", "TEST")
        viewModelScope.launch {
            var pokemons = pokemonLocalRepository.getPokemons().first()

            if (pokemons.isEmpty()) {
                initializePokemons()
            } else {
                _pokedexState.value = pokedexState.value.copy(fullPokemon = pokemons, unknownPokemon = pokemons)
                updateLists()
            }
        }
    }

    private suspend fun initializePokemons(){
        val initialPokemons = pokemonLocalRepository.initialize()
        pokemonLocalRepository.insertAll(initialPokemons)
        val pokemons = pokemonLocalRepository.getPokemons().first()
        Log.d("POKEMONS",pokemons.toString())
        _pokedexState.value = pokedexState.value.copy(fullPokemon = pokemons, unknownPokemon = pokemons)
        updateLists()
    }

    private fun updateLists(){
        _pokedexState.value = _pokedexState.value.copy(viewedPokemon = listOf())
        for(pokemon in _pokedexState.value.fullPokemon){
            if(pokemon.discovered) {
                updateViewedList(pokemon.id)
                updatePokemon(pokemon.id)
                updateUnknownList(pokemon.id)
            }
        }
    }

//    init{
//        viewModelScope.launch {
//            pokemonLocalRepository.getPokemons().collect { pokemons ->
//                _pokedexState.value = pokedexState.value.copy(fullPokemon = pokemons, unknownPokemon = pokemons)
//            }
//        }
//    }

    fun getPokemonByID(id: Int): Pokemon{
        return _pokedexState.value.fullPokemon[id - 1]
    }

    fun addViewedPokemon(id: Int){
        viewModelScope.launch { pokemonLocalRepository.updateDiscoveredPokemon(id) }
        updateUnknownList(id)
        updatePokemon(id)
        updateViewedList(id)
    }

    fun updatePokemon(id: Int){
        val pokemonList = _pokedexState.value.fullPokemon.toMutableList()
        pokemonList[id - 1] = pokemonList[id-1].copy(discovered = true)
        _pokedexState.value.fullPokemon = pokemonList
    }


    fun updateViewedList(id: Int){
        val pokemonList = _pokedexState.value.fullPokemon.toMutableList()
        val pokemon = pokemonList[id-1]
        val pokemonViewedList = _pokedexState.value.viewedPokemon.toMutableList()
        pokemonViewedList += pokemon
        _pokedexState.value = _pokedexState.value.copy(viewedPokemon = pokemonViewedList)
    }
    fun updateUnknownList(id: Int){
        val pokemonList = _pokedexState.value.fullPokemon.toMutableList()
        val pokemon = pokemonList[id-1]
        val pokemonUnknownList = _pokedexState.value.unknownPokemon.toMutableList()
        pokemonUnknownList.remove(pokemon)
        _pokedexState.value = _pokedexState.value.copy(unknownPokemon = pokemonUnknownList)
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

    fun getRandomPokemonList(size: Int, id: Int = 0): List<Pokemon> {
        val pokemonList = _pokedexState.value.fullPokemon.toMutableList()
        pokemonList.removeIf { it.id == id }
        return pokemonList.shuffled().take(size)
    }

    fun resetPokedex(){
        viewModelScope.launch {
            pokemonLocalRepository.deletePokemons()
            initializePokemons()
        }
    }
}

data class PokemonModelState(
    var fullPokemon: List<Pokemon> = listOf(),
    var viewedPokemon: List<Pokemon> = listOf(),
    var unknownPokemon: List<Pokemon> = listOf()
)