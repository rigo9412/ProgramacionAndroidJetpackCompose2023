package com.example.simondiceviewmodel.screeens

import androidx.compose.runtime.MutableState
import kotlin.random.Random

data class GameState (
    val incrementoNivel:Int = 1,
    val incrementoRecord:Int =10,
    val final:Boolean = false,
    var pasosMax:Int =1,
    var record:Int = 0,  //record de usuario
    val nombreJugador: String ="",
    var nivel:Int =1,
    var currentIndex:Int=-1,
    var currentJugadorIndex:Int=0,
//    val botones: MutableList<boton> = mutableListOf(),
    val listaPuntajes: MutableList<Top10State> = mutableListOf(),
    val listaAcciones: MutableList<Accion> = mutableListOf(),
//    val inicio: = listaAcciones.isNotEmpty()
)


