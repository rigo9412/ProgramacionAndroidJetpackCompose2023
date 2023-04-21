package com.example.juegosimondice

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.sp
import com.example.juegosimondice.ui.theme.*
import kotlinx.coroutines.delay
import com.example.juegosimondice.ui.theme.Juego
import com.example.juegosimondice.ui.theme.Jugador
import com.example.juegosimondice.ui.theme.Accion

class MainActivity : ComponentActivity() {
    private  val juego = Juego()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val redAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this, R.raw.rojo
                    )
                )
            }
            var currentActionSimonIndexState by remember { mutableStateOf(juego.curIndex) }
            var resultsState by remember { mutableStateOf<Jugador?>(null) }
            var currentActionPlayer by remember { mutableStateOf(Accion.sinAccion) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(juego.inicio) }
            var startPlayState by remember { mutableStateOf(juego.endSpeak) }
            JuegoSimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(currentActionSimonIndexState) {
                        val action = juego.obtenerCurrentAction()
                        println("${action.toString()} = ${juego.curIndex}")
                        if (startGameState && !currentActionOn && !juego.endSpeak) {
                            when (action) {
//                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Accion.prBotonRojo -> playAudio(redAudio)
//                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
//                                Action.PRESS_BLUE_BUTTON -> playAudio(blueAudio)
                                else -> {
                                    //NO SUENA
                                }
                            }
                            currentActionOn = true
                            delay(1000)
                            currentActionOn = false
                            juego.siguienteAccion()
                            currentActionSimonIndexState = juego.curIndex
                            when (action) {
//                                Action.PRESS_GREEN_BUTTON -> pauseAudio(greenAudio)
                                  Accion.prBotonRojo -> pauseAudio(redAudio)
//                                Action.PRESS_YELLOW_BUTTON -> pauseAudio(yellowAudio)
//                                Action.PRESS_BLUE_BUTTON -> pauseAudio(blueAudio)
                                else -> {
                                    //NO SUENA
                                }
                            }

                        }
                        startPlayState = juego.endSpeak
                    }
                    LaunchedEffect(currentActionPlayer) {
                        if (startGameState && juego.endSpeak && currentActionPlayer != Accion.sinAccion && !currentActionOn) {
                            when (currentActionPlayer) {
//                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                               Accion.prBotonRojo -> playAudio(redAudio)
//                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
//                                Action.PRESS_BLUE_BUTTON -> playAudio(blueAudio)

                                else -> {
                                    //NO SUENA
                                }
                            }
                            currentActionOn = true
                            delay(300)
                            currentActionOn = false

                            if (!juego.validateAction(currentActionPlayer)) {
                                println("END GAME")
                                resultsState = juego.terminar("jugador1")
                                startGameState = juego.inicio
                            }

                            currentActionSimonIndexState = juego.curIndex
                            when (currentActionPlayer) {
//                                Action.PRESS_GREEN_BUTTON -> pauseAudio(greenAudio)
                               Accion.prBotonRojo -> pauseAudio(redAudio)
//                                Action.PRESS_YELLOW_BUTTON -> pauseAudio(yellowAudio)
//                                Action.PRESS_BLUE_BUTTON -> pauseAudio(blueAudio)
                                else -> {
                                    //NO SUENA
                                }
                            }
                            currentActionPlayer = Accion.sinAccion


                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .fillMaxWidth()
                        )
                        SimonDice(
                            game = juego,
                            results = resultsState,
                            actionPlayer = currentActionPlayer,
                            onCurrent = currentActionOn
                        ) {
                            currentActionPlayer = it
                        }
                        Spacer(
                            modifier = Modifier
                                .height(14.dp)
                                .fillMaxWidth()
                        )


                        if (!juego.inicio) {
                            BotonJugar("INICIAR JUEGO", startGameState, onClick = {
                                juego.empezar()
                                currentActionSimonIndexState = juego.curIndex
                                startGameState = juego.inicio
                                resultsState = null

                            })
                        }

                    }
                }
            }
        }
    }
}

private fun playAudio(mp: MediaPlayer) {
    println("AUDIO ${mp.isPlaying}")
    if (!mp.isPlaying) {
        mp.start()
    } else {
        mp.pause();
        mp.seekTo(0);
        mp.start()
    }
}

private fun pauseAudio(mp: MediaPlayer) {
    mp.pause();
    mp.seekTo(0);
}

@Composable
fun SimonDice(
    game: Juego,
    results: Jugador?,
    actionPlayer: Accion,
    onCurrent: Boolean,
    onClick: (action: Accion) -> Unit
) {

    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Status(game, results)
        Score(game, results)
        Recuadros(
            if (game.endSpeak) actionPlayer else game.obtenerCurrentAction(),
            onCurrent,
            enablePlay = game.endSpeak,
            onClick
        )

    }
}


//Mostrar los cuadros que van a interaccionar con el jugador
@Composable
fun Recuadros(action: Accion?, onCurrent: Boolean, enablePlay: Boolean, onClick: (action: Accion) -> Unit){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .width(240.dp)
            .height(130.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
            )
         {
            ColoresBtn(
                on = action == Accion.prBotonAmarillo && onCurrent,
                colorOn = AmarilloOn,
                colorOff = AmarrilloOff,
                colorText = "Amarillo",
                enablePlay = enablePlay,
                rotate = 135f,
                action = Accion.prBotonAmarillo,
                onClick = onClick
            )

//            Box(modifier = Modifier
//                .background(Color.Yellow)
//                .height(110.dp)
//                .width(110.dp))

             ColoresBtn(
                 on = action == Accion.prBotonAzul && onCurrent,
                 colorOn = AzulOn,
                 colorOff = AzulOff,
                 colorText = "Azul",
                 enablePlay = enablePlay,
                 rotate = -135f,
                 action = Accion.prBotonAzul,
                 onClick = onClick
             )
//            Box(modifier = Modifier
//                .background(Color.Blue)
//                .height(110.dp)
//                .width(110.dp))
        }
        Row(modifier = Modifier
            .width(240.dp)
            .height(130.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          
        )
        {
            ColoresBtn(
                on = action == Accion.prBotonVerde && onCurrent,
                colorOn = VerdeOn,
                colorOff = VerdeOff,
                colorText = "Verde",
                enablePlay = enablePlay,
                rotate = 45f,
                action = Accion.prBotonVerde,
                onClick = onClick
            )
//            Box(modifier = Modifier
//                .background(Color.Green)
//                .height(110.dp)
//                .width(110.dp))
            ColoresBtn(
                on = action == Accion.prBotonRojo && onCurrent,
                colorOn = RojoOn,
                colorOff = RojoOff,
                colorText = "Rojo",
                enablePlay = enablePlay,
                rotate = -45f,
                action = Accion.prBotonRojo,
                onClick = onClick
            )
//            Box(modifier = Modifier
//                .background(Color.Red)
//                .height(110.dp)
//                .width(110.dp))
        }
    }

}

@Composable
fun ColoresBtn(
    on: Boolean,
    colorOn: Color,
    colorOff: Color,
    colorText: String,
    enablePlay: Boolean,
    rotate: Float,
    action: Accion,
    onClick: (Accion) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(

        modifier = Modifier
            .width(110.dp)
            .height(110.dp)
            .padding(4.dp)
//            .rotate(rotate)
//            .border(4.dp,colorOn, shape = buttonShape)
////            .background(if (on) colorOn else colorOff)
//            .shadow(
//                elevation = 30.dp,
//                shape = buttonShape,
//                ambientColor = Color.White,
//                spotColor = Color.White
//            )
            .background(
                if (on) colorOn else colorOff
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(action)
            }, contentAlignment = Alignment.Center
    ) {
//        Text(text = colorText, fontWeight = FontWeight(900), color = Color.White)
    }
}


@Composable
fun Status(game: Juego, results: Jugador?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
//        TitleShadow()
//        if (game.started && results == null) {
//            Status(status = "JUEGO INICIADO")
//        } else if (results != null) {
//            Status(status = "JUEGO TERMINADO")
//        }
    }
}


@Composable
fun Score(game: Juego, player: Jugador?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (player != null) {
            Text(
                text = "SCORE: ${player.record}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "LEVEL: ${player.level}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        } else {
            Text(
                text = "SCORE: ${game.rec}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "LEVEL: ${game.niv}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        }

    }
}

@Composable
fun BotonJugar( text: String, startGameState: Boolean, onClick: (action: Accion) -> Unit) {
    OutlinedButton(
        onClick = { }, border = BorderStroke(
            width = 2.dp,
            brush = Brush.horizontalGradient(
                listOf(
                    Color(0xFF42A5F5),
                    Color(0xFFFFA726)
                )
            )
        ),
        enabled = !startGameState
    ) {
        Text("JUGAR", color = Color(0xFF5C6BC0))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun DefaultPreview() {
    JuegoSimonDiceTheme {
        val perdAccion = Accion.prBotonRojo
        val game = Juego()
        SimonDice(game, null,perdAccion, false, onClick = {})
    }
}