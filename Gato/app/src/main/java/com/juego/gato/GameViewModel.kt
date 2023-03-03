package com.juego.gato

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    var estado by mutableStateOf(GameState())

    val tableroIconos: MutableMap<Int, ValorCeldaJugador> = mutableMapOf(
        1 to ValorCeldaJugador.NO_ACTION,
        2 to ValorCeldaJugador.NO_ACTION,
        3 to ValorCeldaJugador.NO_ACTION,
        4 to ValorCeldaJugador.NO_ACTION,
        5 to ValorCeldaJugador.NO_ACTION,
        6 to ValorCeldaJugador.NO_ACTION,
        7 to ValorCeldaJugador.NO_ACTION,
        8 to ValorCeldaJugador.NO_ACTION,
        9 to ValorCeldaJugador.NO_ACTION,
    )

    fun onAction(action: AccionUsuario) {
        when (action) {
            is AccionUsuario.TableroPad -> {
                annadirValorCelda(action.cellNo)
            }
            AccionUsuario.BotonReiniciarCLicked -> {
                reiniciarJuego()
            }
        }
    }

    private fun reiniciarJuego() {
        tableroIconos.forEach { (i, _) ->
            tableroIconos[i] = ValorCeldaJugador.NO_ACTION
        }
        estado = estado.copy(
            descripcionTexto = "Jugador 'O' turno",
            turnoActual = ValorCeldaJugador.CIRCULO,
            tipoVictoria = TipoVictoria.NONE,
            aGanado = false
        )
    }

    private fun annadirValorCelda(cellNo: Int) {
        if (tableroIconos[cellNo] != ValorCeldaJugador.NO_ACTION) {
            return
        }
        if (estado.turnoActual == ValorCeldaJugador.CIRCULO) {
            tableroIconos[cellNo] = ValorCeldaJugador.CIRCULO
            if (revisarSiVictoria(ValorCeldaJugador.CIRCULO)) {
                estado = estado.copy(
                    descripcionTexto = "Jugador 'Mario' Gano",
                    jugadorCirculoPuntuacion = estado.jugadorCirculoPuntuacion + 1,
                    turnoActual = ValorCeldaJugador.NO_ACTION,
                    aGanado = true
                )
            } else if (estaTableroLLeno()) {
                estado = estado.copy(
                    descripcionTexto = "Empate",
                    dibujoCount = estado.dibujoCount + 1
                )
            } else {
                estado = estado.copy(
                    descripcionTexto = "Jugador 'Wario' turno",
                    turnoActual = ValorCeldaJugador.EQUIS
                )
            }
        } else if (estado.turnoActual == ValorCeldaJugador.EQUIS) {
            tableroIconos[cellNo] = ValorCeldaJugador.EQUIS
            if (revisarSiVictoria(ValorCeldaJugador.EQUIS)) {
                estado = estado.copy(
                    descripcionTexto = "Jugador 'X' gano",
                    jugadorEquisPuntuacion = estado.jugadorEquisPuntuacion + 1,
                    turnoActual = ValorCeldaJugador.NO_ACTION,
                    aGanado = true
                )
            } else if (estaTableroLLeno()) {
                estado = estado.copy(
                    descripcionTexto = "Game Draw",
                    dibujoCount = estado.dibujoCount + 1
                )
            } else {
                estado = estado.copy(
                    descripcionTexto = "Jugador 'O' turno",
                    turnoActual = ValorCeldaJugador.CIRCULO
                )
            }
        }
    }

    private fun revisarSiVictoria(valorCelda: ValorCeldaJugador): Boolean {
        when {
            tableroIconos[1] == valorCelda && tableroIconos[2] == valorCelda && tableroIconos[3] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.HORIZONTAL1)
                return true
            }
            tableroIconos[4] == valorCelda && tableroIconos[5] == valorCelda && tableroIconos[6] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.HORIZONTAL2)
                return true
            }
            tableroIconos[7] == valorCelda && tableroIconos[8] == valorCelda && tableroIconos[9] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.HORIZONTAL3)
                return true
            }
            tableroIconos[1] == valorCelda && tableroIconos[4] == valorCelda && tableroIconos[7] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.VERTICAL1)
                return true
            }
            tableroIconos[2] == valorCelda && tableroIconos[5] == valorCelda && tableroIconos[8] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.VERTICAL2)
                return true
            }
            tableroIconos[3] == valorCelda && tableroIconos[6] == valorCelda && tableroIconos[9] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.VERTICAL3)
                return true
            }
            tableroIconos[1] == valorCelda && tableroIconos[5] == valorCelda && tableroIconos[9] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.DIAGONAL1)
                return true
            }
            tableroIconos[3] == valorCelda && tableroIconos[5] == valorCelda && tableroIconos[7] == valorCelda -> {
                estado = estado.copy(tipoVictoria = TipoVictoria.DIAGONAL2)
                return true
            }
            else -> return false
        }
    }

    private fun estaTableroLLeno(): Boolean {
        if (tableroIconos.containsValue(ValorCeldaJugador.NO_ACTION)) return false
        return true
    }
}







