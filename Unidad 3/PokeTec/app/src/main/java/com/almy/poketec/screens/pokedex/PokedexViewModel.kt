package com.almy.poketec.screens.pokedex

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokedexViewModel: ViewModel(){
    private val _uiStatePokemon = MutableStateFlow<Pokemon>( Pokemon())
    val uiStatePokemon: StateFlow<Pokemon> = _uiStatePokemon


    fun asignarPokemon(pokemon: Pokemon){
        _uiStatePokemon.value = _uiStatePokemon.value.copy(
            id = if(pokemon.discover) pokemon.id else 0,
            name = if(pokemon.discover) pokemon.name else "?",
            type1 = if(pokemon.discover) pokemon.type1 else "?",
            type2 = if(pokemon.discover) pokemon.type2 else "?",
            total = if(pokemon.discover) pokemon.total else 0,
            hp = if(pokemon.discover) pokemon.hp else 0,
            attack = if(pokemon.discover) pokemon.attack else 0,
            defense =  if(pokemon.discover) pokemon.defense else 0,
            spAtk = if(pokemon.discover) pokemon.spAtk else 0,
            speed = if(pokemon.discover) pokemon.speed else 0,
            generation = if(pokemon.discover) pokemon.generation else 0,
            legendary = if(pokemon.discover) pokemon.legendary else "?"
        )
    }

    private val _uiState = MutableStateFlow<FormUiState>(
        FormUiState.VistaPokemones(listOf())
    )
    val uiState: StateFlow<FormUiState> = _uiState

    fun PokemonDetails(pokemon : Pokemon){
        _uiState.value = FormUiState.DetallePokemon(pokemon = pokemon)
    }
    fun VistaPokemones(pokemones : List<Pokemon>){
        _uiState.value = FormUiState.VistaPokemones(ListaPokemones = pokemones)
    }

}

data class Pokemon(
    val id: Int = 0,
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val total: Int = 0,
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int= 0,
    val spAtk: Int = 0,
    val speed: Int = 0,
    val generation: Int = 0,
    val legendary: String = "",
    val image:String = "",
    var discover:Boolean = false
)

sealed class FormUiState {
    class VistaPokemones(val ListaPokemones: List<Pokemon>): FormUiState()

    class DetallePokemon(val pokemon: Pokemon): FormUiState()

    class FelicidadesScreen(): FormUiState()

}