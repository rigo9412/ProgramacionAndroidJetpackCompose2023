package com.game.simondicevm.ui

import androidx.compose.ui.graphics.Color

data class SimonUiState(
    val nivel: Int = 1,
    val combinacionJugador: String = "",
    val combinacionSimon: String = "",
    val listaBoton: MutableList<boton> = mutableListOf(),
    val record: Int = 0,
    val mensaje: String = "",
    val listaTop: MutableList<top> = mutableListOf(),
    val nombre: String = ""
)

data class boton(
    var nombre: String = "",
    var colorOn: Color = Color.Gray,
    var colorOff: Color = Color.Gray,
    var encendido: Boolean = false
)

data class top(
    var nombre: String = "",
    var puntos: Int = 0
)

sealed class ScreenUiState {
    object Init : ScreenUiState()
    object Inicio : ScreenUiState()
    class TurnoSimon(
        val nivel: Int
    ) : ScreenUiState()
    class TurnoJugador(
        val mensaje: String,
        val nivel: Int
    ) : ScreenUiState()
    class TopResultados(
        val listaTop: List<top> = listOf(),
    ): ScreenUiState()
    class RegistrarJugador(
        val puntos: Int = 0
    ) : ScreenUiState()
}