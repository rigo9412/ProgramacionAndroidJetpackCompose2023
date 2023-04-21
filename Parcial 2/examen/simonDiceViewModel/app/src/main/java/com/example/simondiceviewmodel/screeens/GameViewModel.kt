package com.example.simondiceviewmodel.screeens

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class GameViewModel:ViewModel() {
    private val _uiState = MutableStateFlow<Screens>(Screens.Init)
    val uiState: StateFlow<Screens> = _uiState
    val juego = Juego()
    var currentActionSimonIndexState by  mutableStateOf(juego.curIndex)
    var resultsState by  mutableStateOf<Jugador?>(null)
    var currentActionPlayer by  mutableStateOf(Accion.sinAccion)
    var currentActionOn by  mutableStateOf(false)
    var startGameState by mutableStateOf(juego.inicio)
    var startPlayState by  mutableStateOf(juego.endSpeak)
    var state by mutableStateOf(GameState())

    init {
    }

    fun obtenerCurrentAction(): Accion{
        return if(state.listaAcciones.isNotEmpty() && state.currentIndex >=0 && state.currentIndex<= state.listaAcciones.lastIndex)
            state.listaAcciones[state.currentIndex] else Accion.sinAccion
    }

    fun siguienteAccion(): Accion{
        if(state.listaAcciones.isEmpty() || state.currentIndex > state.listaAcciones.lastIndex) return Accion.sinAccion
        if (state.currentIndex == state.listaAcciones.lastIndex){
            state.currentIndex++
            return Accion.sinAccion
        }
        state.currentIndex++
        return  state.listaAcciones[state.currentIndex]
    }

    fun generarAcciones() {
        state.listaAcciones.clear()
        for (i in 1.. state.pasosMax){
            state.listaAcciones.add(Accion.values()[Random.nextInt(1, Accion.values().count())])
        }
    }
    fun terminar(nomjugador: String): Jugador{
        var jugador =Jugador(nomjugador,state.record,state.nivel)
        state.record=0
        state.nivel=0
        state.pasosMax=1
        state.currentIndex=-1
        state.currentJugadorIndex= -1
        state.listaAcciones.clear()
        return jugador
    }

    fun validateAction(action: Accion): Boolean{
        if(state.listaAcciones[state.currentJugadorIndex] != action) return  false

        if(state.listaAcciones.lastIndex == state.currentJugadorIndex){
            levelUp()
        }else{
            state.currentJugadorIndex++
        }

        return true
    }

    private fun levelUp(){
        state.pasosMax += 1
        state.nivel++
        state.record += (state.incrementoRecord * state.nivel)
        state.currentIndex = -1
        state.currentJugadorIndex = 0
        generarAcciones()
    }
}




