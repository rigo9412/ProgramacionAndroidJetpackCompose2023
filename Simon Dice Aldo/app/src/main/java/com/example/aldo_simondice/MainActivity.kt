package com.example.aldo_simondice

import android.media.MediaPlayer                    //Importacion para MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aldo_simondice.domain.models.Accion
import com.example.aldo_simondice.domain.models.Jugador     //Importacion de la clase jugador
import com.example.aldo_simondice.domain.models.Videojuevo  //Importacion de la clase Videojuego
import com.example.aldo_simondice.ui.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    //Cada vez que se inicia el juego debemos de definir los valores iniciales
    private val videojuego = Videojuevo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Definimos nuestros audios
            val RosaAudio: MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(this,R.raw.rosa))}
            val MoradoAudio: MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(this,R.raw.morado))}
            val AzuladoAudio: MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(this,R.raw.azulado))}
            val NaranjaAudio: MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(this,R.raw.naranja))}
            var nombreIngresado by remember{ mutableStateOf("") }

            var mejoresJugadas by remember{ mutableStateOf(videojuego.obtenerPuntuaciones) }


            var actualAccionSimonIndexEstado by remember{ mutableStateOf(videojuego.actualAccionSimonIndex) }
            var resultadosxEstado by remember{ mutableStateOf<List<MutableMap.MutableEntry<String, Int>>>(listOf()) }
            var actualAccionJugador by remember{ mutableStateOf(Accion.NO_ACCION) }
            var actualAccionEncendido by remember{ mutableStateOf(false) }
            var empezarVideojuegoEstado  by remember { mutableStateOf(videojuego.comienzo) }
            var empezarJugarEstado by remember { mutableStateOf(videojuego.endSpeak) } //para que sirve el endSpeak?

            AldoSimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    
                    //PATRON QUE SE TIENE QUE REPETIR
                    LaunchedEffect(actualAccionSimonIndexEstado ){
                        val accion = videojuego.obtenerActualAccion()
                        println("${accion.toString()} = ${videojuego.actualAccionSimonIndex}")
                        if(empezarVideojuegoEstado && !actualAccionEncendido && !videojuego.endSpeak){
                            when (accion){
                                Accion.PRESIONAR_BOTON_Morado -> reproducirAudio(MoradoAudio)
                                Accion.PRESIONAR_BOTON_Naranja -> reproducirAudio(NaranjaAudio)
                                Accion.PRESIONAR_BOTON_Azulado -> reproducirAudio(AzuladoAudio)
                                Accion.PRESIONAR_BOTON_Rosa -> reproducirAudio(RosaAudio)
                                else -> {
                                    //NO SONAR
                                }
                            }
                            actualAccionEncendido = true
                            delay(1000)
                            actualAccionEncendido = false
                            videojuego.moverSiguienteAccion()
                            actualAccionSimonIndexEstado = videojuego.actualAccionSimonIndex
                        }
                        empezarJugarEstado = videojuego.endSpeak
                    }
                    //PATRON INGRESADO POR EL USUARIO
                    LaunchedEffect(actualAccionJugador) {
                        if (empezarJugarEstado && videojuego.endSpeak && actualAccionJugador != Accion.NO_ACCION && !actualAccionEncendido) {
                            when (actualAccionJugador) {
                                Accion.PRESIONAR_BOTON_Morado -> reproducirAudio(MoradoAudio)
                                Accion.PRESIONAR_BOTON_Naranja -> reproducirAudio(NaranjaAudio)
                                Accion.PRESIONAR_BOTON_Azulado -> reproducirAudio(AzuladoAudio)
                                Accion.PRESIONAR_BOTON_Rosa -> reproducirAudio(RosaAudio)

                                else -> {
                                    //NO SUENA
                                }
                            }
                            actualAccionEncendido = true
                            delay(300)
                            actualAccionEncendido = false

                            if(!videojuego.validarAccion(actualAccionJugador)){
                                println("FIN DEL JUEGOOOOO!!!")
                                resultadosxEstado = videojuego.final()
                                empezarJugarEstado = videojuego.comienzo
                            }

                            actualAccionSimonIndexEstado = videojuego.actualAccionSimonIndex
                            actualAccionJugador = Accion.NO_ACCION
                        }
                    }

                    Column() {
                        CustomInput(nombreIngresado){
                            videojuego.cambiarNombre(it)
                        }
                        SimonJuego(
                            videojuego,
                            nombreIngresado,
                            empezarJugarEstado,
                            videojuego.obtenerActualAccion(),
                            accionJugador = actualAccionJugador,
                            actualAccionEncendido
                        ){
                            actualAccionJugador = it

                        }
                        EmpezarBoton(empezarVideojuegoEstado, onStart = {
                            videojuego.empezar()
                            actualAccionSimonIndexEstado = videojuego.actualAccionSimonIndex
                            empezarVideojuegoEstado = videojuego.comienzo
                        })

                        Column(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            for (jugada in mejoresJugadas) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text("${jugada.key}: ${jugada.value}")
                                }
                            }
                        }


                    }
                }
            }
        }
    }
}

//Reproducir audio
private fun reproducirAudio(mp: MediaPlayer) {
    println("AUDIO ${mp.isPlaying}")
    if(!mp.isPlaying) {
        mp.start()
    }else{
        mp.pause();
        mp.seekTo(0);
        mp.start()
    }
}



@Composable
fun SimonJuego(videojuego: Videojuevo,nombreIngresado: String, endSpeak: Boolean, accion: Accion,accionJugador:Accion, offCurrent: Boolean, onClick: (accion: Accion) -> Unit) {

    Column(
        modifier = Modifier.background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Pad(if(endSpeak) accionJugador else accion, offCurrent,enablePlay = endSpeak,onClick)

    }
}


@Composable
fun Pad(accion: Accion?, onCurrent: Boolean, enablePlay: Boolean,onClick: (accion: Accion) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            BotonAccion(
                on = accion == Accion.PRESIONAR_BOTON_Morado && onCurrent,
                colorOn = MoradoEncendido,
                colorOff = MoradoApagado,
                colorText = "Morado",
                enablePlay = enablePlay,
                accion = Accion.PRESIONAR_BOTON_Morado,
                onClick =  onClick
            )
            BotonAccion(
                on = accion == Accion.PRESIONAR_BOTON_Naranja && onCurrent,
                colorOn = NaranjaEncendido,
                colorOff = NaranjaApagado,
                colorText = "Naranja",
                enablePlay = enablePlay,
                accion = Accion.PRESIONAR_BOTON_Naranja,
                onClick = onClick

            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            BotonAccion(
                on = accion == Accion.PRESIONAR_BOTON_Rosa && onCurrent,
                colorOn = RosaEncendido,
                colorOff = RosaApagado,
                colorText = "Rosa",
                enablePlay = enablePlay,
                accion = Accion.PRESIONAR_BOTON_Rosa,
                onClick = onClick
            )
            BotonAccion(
                on = accion == Accion.PRESIONAR_BOTON_Azulado && onCurrent,
                colorOn = AzuladoEncendido,
                colorOff = AzuladoApagado,
                colorText = "Azulado",
                enablePlay = enablePlay,
                accion = Accion.PRESIONAR_BOTON_Azulado,
                onClick = onClick
            )
        }
    }
}

@Composable
fun BotonAccion(
    on: Boolean,
    colorOn: Color,
    colorOff: Color,
    colorText: String,
    enablePlay: Boolean,
    accion: Accion,
    onClick: (Accion) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(

        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .padding(4.dp)
            .background(if (on) colorOn else colorOff)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(accion)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = colorText, fontWeight = FontWeight(900))
    }
}


@Composable
fun EmpezarBoton(empezarVideojuegoEstado: Boolean, onStart: () -> Unit) {
    Button(
        enabled = true,
        onClick = { onStart() }) {
        Text(text = "INICIAR")
    }
}


@Composable
fun CustomInput(text : String,OnTextFieldChangued:(String)-> Unit){
    TextField(value = text, onValueChange = {OnTextFieldChangued(it)})
}
