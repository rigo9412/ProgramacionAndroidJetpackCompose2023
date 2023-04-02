package com.tec.pokedexapp.ui.game

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tec.pokedexapp.data.PokemonLocalRepository
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.ui.pokemon.PokemonViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class GameViewModel(
    private val pokemonViewModel: PokemonViewModel
) : ViewModel() {

    private val _score = MutableStateFlow(0)
    val score : StateFlow<Int> = _score

    private val _lives = MutableStateFlow(3)
    val lives : StateFlow<Int> = _lives

    private val _gameState = MutableStateFlow(GameState.START)
    val gameState: StateFlow<GameState> = _gameState

    private val _pokemonOptions = MutableStateFlow<List<Pokemon>>(listOf())
    val pokemonOptions: StateFlow<List<Pokemon>> = _pokemonOptions

    private val _currentPokemon = MutableStateFlow<Pokemon?>(Pokemon(id = 1))
    val currentPokemon: StateFlow<Pokemon?> = _currentPokemon

    var tempScore = 0
    var started = false
    var finished = false

    private val answerTimer : Long = 5000
    private val resultTimer : Long = 1500

    private var guessJob: Job? = null

    fun startRound(){
        _gameState.value = GameState.START
        Log.d("POKEMONS",pokemonViewModel.pokedexState.value.unknownPokemon.toString())
        Log.d("POKEMONS",pokemonViewModel.pokedexState.value.viewedPokemon.toString())
        _currentPokemon.value = pokemonViewModel.getRandomUnknownPokemon()
        if(_currentPokemon.value != null) {
            _pokemonOptions.value =
                (pokemonViewModel.getRandomPokemonList(3) + _currentPokemon.value!!).shuffled()
            CoroutineScope(Dispatchers.Default).launch {
                guessPokemon()
            }
        }
        else{
            _gameState.value = GameState.END
        }
    }

    suspend fun guessPokemon(){
        if(_currentPokemon.value != null) {
            _gameState.value = GameState.GUESSING
            guessJob = viewModelScope.launch {
                delay(answerTimer)
                _lives.value -= 1
                showResult()
            }
        }
        else{
            _gameState.value = GameState.END
        }
    }

    suspend fun makeGuess(id: Int){
        guessJob?.cancel()
        if(id == _currentPokemon.value?.id){
            _score.value += 1
            pokemonViewModel.addViewedPokemon(_currentPokemon.value!!.id)
            _currentPokemon.value = pokemonViewModel.pokedexState.value.fullPokemon[_currentPokemon.value!!.id - 1]
        }
        else{
            _lives.value -= 1
        }
        showResult()
    }

    suspend fun showResult(){
        _gameState.value = if(_lives.value < 1) GameState.LOST else _gameState.value
        var tempState = _gameState.value
        tempScore = _score.value

        _gameState.value = GameState.RESULT
        delay(resultTimer)

        _gameState.value = tempState
        when(_gameState.value){
            GameState.END -> stopGame()
            GameState.LOST -> stopGame()
            else -> startRound()
        }
    }

    fun stopGame(){
        guessJob?.cancel()
        _lives.value = 3
        _score.value = 0
    }
}


enum class GameState{
    START,GUESSING,RESULT,LOST,END
}