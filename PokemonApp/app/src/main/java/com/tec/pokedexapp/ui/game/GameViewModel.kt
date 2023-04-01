package com.tec.pokedexapp.ui.game

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.tec.pokedexapp.data.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _gameModelState = MutableStateFlow<GameModelState>(GameModelState())
    val gameModelState: StateFlow<GameModelState> = _gameModelState

    private val _gameState = MutableStateFlow<GameState>(GameState.START)
    val gameState: StateFlow<GameState> = _gameState

    private val _pokemonOptions = MutableStateFlow<List<Pokemon>>(listOf())
    val pokemonOptions: StateFlow<List<Pokemon>> = _pokemonOptions

//    private val _currentPokemonID = MutableStateFlow<Int>(0)
//    val currentPokemonID: StateFlow<Int> = _currentPokemonID

    var currentPokemon : Pokemon? = null
    var answered = false
    var answer = 0
    var answerResult = false
    private val answerTimer : Long = 5000
    private val resultTimer : Long = 1500

    fun startRound(correctPokemon: Pokemon, options: List<Pokemon>){
        if(_gameModelState.value.lives > 0){
            CoroutineScope(Dispatchers.Default).launch {
                guessPokemon(correctPokemon, options)
            }
        }
        else{
            _gameState.value = GameState.LOST
        }
    }

    suspend fun guessPokemon(correctPokemon: Pokemon?, options: List<Pokemon>){
        if(correctPokemon != null) {
            _gameState.value = GameState.GUESSING
            currentPokemon = correctPokemon
            _pokemonOptions.value = (options + correctPokemon).shuffled()

            CoroutineScope(Dispatchers.Default).launch {
                delay(answerTimer)
                showResult()
            }

            if (answered) {
                showResult()
            }
        }
        else{
            GameState.END
        }
    }

    fun makeGuess(id: Int){
        answered = true
        if(id == currentPokemon?.id){
            answerResult = true
        }
        else{
            answerResult = false
            _gameModelState.value.copy(lives = _gameModelState.value.lives - 1)
        }
    }

    suspend fun showResult(){
        _gameState.value = GameState.RESULT
        delay(resultTimer)
    }

    fun resetRound(){
        currentPokemon = null
        answered = false
        answer = 0
        answerResult = false
    }
}

data class GameModelState(
    val score: Int = 0,
    val lives: Int = 3
)

enum class GameState{
    START,GUESSING,RESULT,LOST,END
}