package com.almy.simondice

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.almy.simondice.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.timerTask
import kotlin.random.Random

class SimonDiceViewModel: ViewModel(){
    private val _uiStateSimonDice = MutableStateFlow<SimonDice>(SimonDice())
    val uiStateSimonDice: StateFlow<SimonDice> = _uiStateSimonDice

    fun cambiarTurno(turno: String) {
        if (turno == "Turno de simon!")
            _uiStateSimonDice.value = _uiStateSimonDice.value.copy(turno = "Tu turno!")
        else
            _uiStateSimonDice.value = _uiStateSimonDice.value.copy(turno = "Turno de simon!")
    }

    fun incrementarNivel() {
        _uiStateSimonDice.value = _uiStateSimonDice.value.copy(nivel = _uiStateSimonDice.value.nivel + 1)
    }

    fun incrementarPuntuacion(){
        _uiStateSimonDice.value = _uiStateSimonDice.value.copy(puntuacionFinal = _uiStateSimonDice.value.puntuacionFinal + 100)
    }

    fun incrementarPatronDeSimon(){
        val numeroNuevo = Random.nextInt(1,5)
        _uiStateSimonDice.value = _uiStateSimonDice.value.copy(simonPatron = _uiStateSimonDice.value.simonPatron + numeroNuevo.toString())
    }

      fun iniciarPatronSimon(numeroBoton: Char){
          var botonesColoresActuales = _uiStateSimonDice.value.botonesColores

        if(numeroBoton == '1'){
            botonesColoresActuales.put(1, verdeON)
            _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)

            Timer().schedule(timerTask {
                botonesColoresActuales.put(1, verdeOFF)
                _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)
            }, 2000)
        }
         else if(numeroBoton == '2'){
            botonesColoresActuales.put(2, azulON)
            _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)

            Timer().schedule(timerTask {
                botonesColoresActuales.put(2, azulOFF)
                _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)

            }, 1000)
        }
        else if(numeroBoton == '3'){
            botonesColoresActuales.put(3, rojoON)
            _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)

            Timer().schedule(timerTask {
                botonesColoresActuales.put(3, rojoOFF)
                _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)

            }, 1000)
        }
        else if(numeroBoton == '4'){
            botonesColoresActuales.put(4, amarilloON)
            _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)

            Timer().schedule(timerTask {
                botonesColoresActuales.put(4, amarilloOFF)
                _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesColores = botonesColoresActuales)

            }, 2000)
        }

    }

    fun concatenarPatronJugador(patron: String){
        _uiStateSimonDice.value = _uiStateSimonDice.value.copy(jugadorPatron = _uiStateSimonDice.value.jugadorPatron + patron)

        if (_uiStateSimonDice.value.jugadorPatron.length == _uiStateSimonDice.value.simonPatron.length){
           sonIgualesLosPatrones()
        }

    }

    fun habilitarBotones(){
        var botonesHabilitados = _uiStateSimonDice.value.botonesHabilitados

        botonesHabilitados.put(1, true)
        botonesHabilitados.put(2, true)
        botonesHabilitados.put(3, true)
        botonesHabilitados.put(4, true)

        _uiStateSimonDice.value = _uiStateSimonDice.value.copy(botonesHabilitados = botonesHabilitados)
    }

    fun sonIgualesLosPatrones(){
        if(_uiStateSimonDice.value.simonPatron == _uiStateSimonDice.value.jugadorPatron){
            incrementarNivel()
            incrementarPuntuacion()
            incrementarPatronDeSimon()
        }
        else
        {
            reiniciarJuego()
        }
    }

    fun reiniciarJuego(){
        _uiStateSimonDice.value = _uiStateSimonDice.value.copy(
            turno = "Tu turno!",
            simonPatron = Random.nextInt(1,5).toString(),
            jugadorPatron = "",
            estadoPartida = "",
            puntuacionFinal = 0,

            nivel = 1,

        )
    }

}

data class SimonDice(
    val turno: String = "Turno de simon!",
    val simonPatron: String = Random.nextInt(1,5).toString(),
    val jugadorPatron: String = "",
    val estadoPartida: String = "",
    val puntuacionFinal: Int = 0,
    val nivel: Int = 1,
    val botones: HashMap<Int,String> = hashMapOf(
        1 to "1",
        2 to "2",
        3 to "3",
        4 to "4"),

    val botonesColores: HashMap<Int, Color> = hashMapOf(
        1 to verdeOFF,
        2 to azulOFF,
        3 to rojoOFF,
        4 to amarilloOFF),

    val botonesHabilitados: HashMap<Int, Boolean> = hashMapOf(
        1 to false,
        2 to false,
        3 to false,
        4 to false)
)