package com.otop.SimonDice.ui

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.otop.SimonDice.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class MainViewModel : ViewModel() {

    var isPlaying = mutableStateOf(false)
    var sequence = mutableStateOf( mutableListOf<Int> ())
    var currentTurn = mutableStateOf( 0 )
    var level = mutableStateOf(0)
    var score = mutableStateOf(0)

    var btnBool = mutableStateOf(true)

    var redPressed = mutableStateOf(false)
    var greenPressed = mutableStateOf(false)
    var bluePressed = mutableStateOf(false)
    var yellowPressed = mutableStateOf(false)

    var gameOver = mutableStateOf(false)
    var enaButtons = mutableStateOf(false)

    var isSpeaking = mutableStateOf(false)

    var roundEnd = mutableStateOf(false )

    var toptenNombre = mutableStateOf(mutableListOf<String>("Nombre","Nombre","Nombre","Nombre","Nombre","Nombre","Nombre","Nombre","Nombre","Nombre"))
    var toptenScore = mutableStateOf(mutableListOf<Int>(0,0,0,0,0,0,0,0,0,0))

    var topName = mutableStateOf("")

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Game)
    val currentScreen: StateFlow<Screen> = _currentScreen

    fun navigateToGame() {
        _currentScreen.value = Screen.Game
    }

    fun navigateToTop() {
        _currentScreen.value = Screen.Top
    }

    fun colorTrigger(pressed: Int, isSequence:Boolean): Unit {
        if ( isSequence ){
            when(pressed){
                0 -> {
                    redPressed.value = true
                }
                1 -> {
                    greenPressed.value = true
                }
                2 -> {
                    bluePressed.value = true
                }
                3 -> {
                    yellowPressed.value = true
                }
            }
        }
        else{
            when(pressed){
                0 -> {
                    redPressed.value = true
                    if (sequence.value[currentTurn.value] != 0) gameOver.value = true;  isPlaying.value = false
                }
                1 -> {
                    greenPressed.value = true
                    if (sequence.value[currentTurn.value] != 1) gameOver.value = true;  isPlaying.value = false
                }
                2 -> {
                    bluePressed.value = true
                    if (sequence.value[currentTurn.value] != 2) gameOver.value = true ; isPlaying.value = false
                }
                3 -> {
                    yellowPressed.value = true
                    if (sequence.value[currentTurn.value] != 3) gameOver.value = true; isPlaying.value = false
                }
            }
            if (currentTurn.value == sequence.value.size-1) {
                currentTurn.value = 0
                roundEnd.value = true
                return
            }
            currentTurn.value++
        }
    }
    fun nextRound(): Unit {
        level.value++
        println(level.value)
        var auxs = mutableListOf<Int>()
        for ( i in 0..level.value ){
            var rand = Random.nextInt(0,4)
            auxs.add(rand)
        }
        sequence.value = auxs
    }
    fun StartGame(): Unit {
        btnBool.value = false
        nextRound()
    }

    fun updateTop() {
        var i = 0
        var newDataScore = mutableStateOf(mutableListOf<Int>())
        var newDataName = mutableStateOf(mutableListOf<String>())
        var input = false
        toptenScore.value.sortDescending()
        for ( e in toptenScore.value){
            if (e <= level.value && !input){
                input = true
                newDataName.value.add(topName.value)
                newDataScore.value.add(level.value)
            }
            else{
                newDataName.value.add(toptenNombre.value[i])
                newDataScore.value.add(e)
            }
            i++
        }
        toptenScore.value = newDataScore.value
        toptenNombre.value = newDataName.value
        btnBool.value = true
        level.value = 0
        gameOver.value = false
    }
}




sealed class Screen(val route: String) {
    object Game: Screen("game")
    object Top: Screen("Top")
}