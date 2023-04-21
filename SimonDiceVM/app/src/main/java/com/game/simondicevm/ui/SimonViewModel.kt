package com.game.simondicevm.ui

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.game.simondicevm.R
import com.game.simondicevm.ui.theme.DarkBlue
import com.game.simondicevm.ui.theme.DarkGreen
import com.game.simondicevm.ui.theme.DarkRed
import com.game.simondicevm.ui.theme.DarkYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class SimonViewModel : ViewModel() {
    //variable que indica el estado de los datos
    private val _uiStateData = MutableStateFlow<SimonUiState>(SimonUiState())
    val uiStateData: StateFlow<SimonUiState> = _uiStateData

    //variable que indica el estado de la interfaz
    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState> = _uiState

    fun GenerarCombinacion(nvl: Int): String {
        var combinacion = ""
        //var valor = 0; var valorPrevio = 0
        var i = nvl
        while (i != 0) {
            combinacion = combinacion + Random.nextInt(4).toString()
            i--
        }
        return combinacion
    }

    init {
        Inicio()
    }

    fun Inicio() {
        _uiState.value = ScreenUiState.Inicio
    }

    fun PrepararBotones() {
        _uiState.value = ScreenUiState.TurnoSimon(1)
        var botones: MutableList<boton> = mutableListOf()
        var btn1: boton = boton()
        btn1.nombre = "Verde"
        btn1.encendido = true
        btn1.colorOff = DarkGreen
        btn1.colorOn = Color.Green

        var btn2: boton = boton()
        btn2.nombre = "Rojo"
        btn2.encendido = true
        btn2.colorOff = DarkRed
        btn2.colorOn = Color.Red

        var btn3: boton = boton()
        btn3.nombre = "Azul"
        btn3.encendido = true
        btn3.colorOff = DarkBlue
        btn3.colorOn = Color.Blue

        var btn4: boton = boton()
        btn4.nombre = "Amarillo"
        btn4.encendido = true
        btn4.colorOff = DarkYellow
        btn4.colorOn = Color.Yellow

        botones.add(btn1); botones.add(btn2); botones.add(btn3); botones.add(btn4)

        _uiStateData.value = _uiStateData.value.copy(listaBoton = botones)
    }

    suspend fun reproducirCombinacion(combinacion: String, context: Context) {
        //guardar la combinacion de simon
        _uiStateData.value = _uiStateData.value.copy(combinacionSimon = combinacion)

        //recorrer la cadena para reproducir la combinacion
        var botones: MutableList<boton> = _uiStateData.value.listaBoton
        for (n in combinacion) {
            when (n) {
                '0' -> {
                    delay(200)
                    botones[0].encendido = false
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )
                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                    reproducirSonido(0, context)
                    delay(1000)
                    botones[0].encendido = true
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )
                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                }
                '1' -> {
                    delay(200)
                    botones[1].encendido = false
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )
                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                    reproducirSonido(1, context)
                    delay(1000)
                    botones[1].encendido = true
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )
                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                }
                '2' -> {
                    delay(200)
                    botones[2].encendido = false
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )
                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                    reproducirSonido(2, context)
                    delay(1000)
                    botones[2].encendido = true
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )

                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                }
                '3' -> {
                    delay(200)
                    botones[3].encendido = false
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )
                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                    reproducirSonido(3, context)
                    delay(1000)
                    botones[3].encendido = true
                    _uiStateData.value = _uiStateData.value.copy(
                        listaBoton = botones
                    )
                    _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
                }
            }
        }

        //esperar tantito y pasar el turno al jugador
        delay(200)
        _uiStateData.value = _uiStateData.value.copy(mensaje = "Tu turno ;)")
        _uiState.value =
            ScreenUiState.TurnoJugador(_uiStateData.value.mensaje, _uiStateData.value.nivel)
    }

    suspend fun Evaluar(btnPresionado: String, context: Context) {
        //guardar el boton presionado por el jugador
        var combinacion = _uiStateData.value.combinacionJugador
        _uiStateData.value =
            _uiStateData.value.copy(combinacionJugador = combinacion + btnPresionado)
        var msj = ""

        //encender boton presionado
        var botones: MutableList<boton> = _uiStateData.value.listaBoton
        when (btnPresionado) {
            "0" -> {
                reproducirBoton(botones, 0, msj, context)
            }
            "1" -> {
                reproducirBoton(botones, 1, msj, context)
            }
            "2" -> {
                reproducirBoton(botones, 2, msj, context)
            }
            "3" -> {
                reproducirBoton(botones, 3, msj, context)
            }
        }
    }

    suspend fun reproducirBoton(botones: MutableList<boton>, n: Int, msj1: String, context: Context) {
        var msj = msj1
        botones[n].encendido = false
        _uiStateData.value = _uiStateData.value.copy(
            listaBoton = botones
        )

        //checar si el jugador ya termino su turno
        if (ComprobarLenght()) {
            //ahora checar si acertó o se equivocó
            if (_uiStateData.value.combinacionSimon == _uiStateData.value.combinacionJugador) {
                msj = SacarFelicitacion()
                _uiStateData.value = _uiStateData.value.copy(mensaje = msj)
                _uiState.value = ScreenUiState.TurnoJugador(_uiStateData.value.mensaje, _uiStateData.value.nivel)
                reproducirSonido(n, context)
                delay(1000)
                botones[n].encendido = true
                var nvl = _uiStateData.value.nivel
                _uiStateData.value = _uiStateData.value.copy(
                    listaBoton = botones,
                    combinacionJugador = "",
                    combinacionSimon = "",
                    nivel = nvl + 1
                )
                delay(500)
                _uiState.value = ScreenUiState.TurnoSimon(_uiStateData.value.nivel)
            } else {
                msj = "Incorrecto :c"
                var record = _uiStateData.value.nivel
                _uiStateData.value = _uiStateData.value.copy(mensaje = msj, record = record - 1)
                _uiState.value = ScreenUiState.TurnoJugador(_uiStateData.value.mensaje, _uiStateData.value.nivel)
                reproducirSonido(n, context)
                delay(1000)
                botones[n].encendido = true
                var nvl = _uiStateData.value.nivel

                //checar si esta dentro del top 10
                if(_uiStateData.value.listaTop.size <= 10)
                {
                    _uiState.value = ScreenUiState.RegistrarJugador(_uiStateData.value.record)
                }
                else{
                    var n = 0
                    for (i in _uiStateData.value.listaTop.sortedByDescending { it.puntos })
                    {
                        if(i.puntos < _uiStateData.value.record && n<10)
                        {
                            _uiState.value = ScreenUiState.RegistrarJugador(_uiStateData.value.record)
                        }
                        n++
                    }
                }

                _uiStateData.value = _uiStateData.value.copy(
                    listaBoton = botones,
                    combinacionJugador = "",
                    combinacionSimon = "",
                    nivel = 1
                )
                delay(500)
                _uiState.value = ScreenUiState.Inicio
            }
        } else {
            msj = "Tu turno ;)"
            _uiStateData.value = _uiStateData.value.copy(mensaje = msj)
            _uiState.value = ScreenUiState.TurnoJugador(_uiStateData.value.mensaje, _uiStateData.value.nivel)
            reproducirSonido(n, context)
            delay(1000)
            botones[n].encendido = true
            _uiStateData.value = _uiStateData.value.copy(
                listaBoton = botones
            )
            _uiState.value =
                ScreenUiState.TurnoJugador(_uiStateData.value.mensaje, _uiStateData.value.nivel)
        }
    }

    fun reproducirSonido(btn: Int, context: Context) {
        when (btn) {
            0 -> MediaPlayer
                .create(context, R.raw.sound1)
                .start()
            1 -> MediaPlayer
                .create(context, R.raw.sound2)
                .start()
            2 -> MediaPlayer
                .create(context, R.raw.sound3)
                .start()
            3 -> MediaPlayer
                .create(context, R.raw.sound4)
                .start()
        }
    }


    fun SacarFelicitacion(): String {
        var frases = listOf("¡Excelente!", "¡Correcto!", "¡Bien hecho!", "¡Wow!", "¡Sigue así!")
        var frase = frases[Random.nextInt(5)]
        return frase
    }

    fun ComprobarLenght(): Boolean {
        return _uiStateData.value.combinacionSimon.length == _uiStateData.value.combinacionJugador.length
    }

    fun actualizarNombre(name: String)
    {
        _uiStateData.value = _uiStateData.value.copy(nombre = name)
    }

    fun RegistrarJugador(
        puntos: Int
    ){
        var listaTop: MutableList<top> = _uiStateData.value.listaTop
        listaTop.add(top(_uiStateData.value.nombre, puntos))

        _uiStateData.value = _uiStateData.value.copy(
            listaTop = listaTop,
            nombre = ""
        )
        _uiState.value = ScreenUiState.Inicio
    }

    fun MostrarTop()
    {
        _uiState.value = ScreenUiState.TopResultados(_uiStateData.value.listaTop)
    }
     fun RegresarInicio()
     {
         _uiState.value = ScreenUiState.Inicio
     }
}