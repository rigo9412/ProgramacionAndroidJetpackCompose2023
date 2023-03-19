package com.example.simondice

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.simondice.domain.models.*
import com.example.simondice.ui.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val blueAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.azul)) }
            val redAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.rojo)) }
            val yellowAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.amarillo)) }
            val greenAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.verde)) }

            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var resultsxState by remember { mutableStateOf<Player?>(null) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }

            var mensaje = false
            var verficiar = false

            SimonDiceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(currentActionSimonIndexState) {
                        val action = game.getCurrentAction()
                        println("${action.toString()} = ${game.currentActionSimonIndex}")
                        if (startGameState && !currentActionOn && !game.endSpeak) {
                            when (action) {
                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)
                                else -> {
                                    //NO SUENA
                                }
                            }
                            currentActionOn = true
                            delay(1000)
                            currentActionOn = false
                            game.moveToNextAction()
                            currentActionSimonIndexState = game.currentActionSimonIndex

                        }
                        startPlayState = game.endSpeak
                    }

                    LaunchedEffect(currentActionPlayer) {
                        if (startGameState && game.endSpeak && currentActionPlayer != Action.NO_ACTION && !currentActionOn) {
                            when (currentActionPlayer) {
                                Action.PRESS_GREEN_BUTTON ->  playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)

                                else -> {
                                    //NO SUENA
                                }
                            }
                            currentActionOn = true
                            delay(300)
                            currentActionOn = false

                            if(!game.validateAction(currentActionPlayer)){
                                resultsxState = game.end("Fin")
                                mensaje = true
                                startGameState = game.started
                            }

                            currentActionSimonIndexState = game.currentActionSimonIndex
                            currentActionPlayer = Action.NO_ACTION

                        }
                    }

                    Column() {
                        SimonGame(
                            game.level,
                            game.score,
                            startPlayState,
                            game.getCurrentAction(),
                            actionPlayer = currentActionPlayer,
                            currentActionOn) {
                            currentActionPlayer = it
                        }
                        StartButton(startGameState, onStart = {
                            game.start()
                            currentActionSimonIndexState = game.currentActionSimonIndex
                            startGameState = game.started
                            mensaje = false
                        })
                        if (mensaje){
                            verficiar = alertDialogSample("¡Perdiste!",
                                resultsxState!!.score.toString(), resultsxState!!.level.toString())
                        }
                        if (verficiar){mensaje = false}
                    }
                }
            }
        }
    }
}
@Composable
fun alertDialogSample(name : String,score : String,level : String) :Boolean {
    var x = false
    val activity = (LocalContext.current as? Activity)
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true)  }
            if (openDialog.value){
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = name,fontSize = 35.sp,fontWeight = FontWeight(600))
                    },
                    text = {
                        Text("\nNivel alcanzado: "+level+"\nPuntaje final: "+score,fontSize = 25.sp)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                openDialog.value = false
                                x = true
                            }) {
                            Text("Continuar",fontSize = 20.sp, color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                activity?.finish()
                            }) {
                            Text("Salir",fontSize = 20.sp, color = Color.White)
                        }
                    }
                )
            }
        }
    }
    return x
}

private fun playAudio(mp: MediaPlayer) {
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
fun SimonGame(level : Int,score : Int,endSpeak: Boolean, action: Action,actionPlayer:Action, offCurrent: Boolean, onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Pad(level,score,if(endSpeak) actionPlayer else action, offCurrent,enablePlay = endSpeak,onClick)

    }
}

@Composable
fun Pad(level : Int,score : Int,action: Action?, onCurrent: Boolean, enablePlay: Boolean,onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier.height(400.dp), verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "SCORE: $score   LEVEL: $level", fontWeight = FontWeight(700),fontSize = 27.sp, color = Color.White)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_GREEN_BUTTON && onCurrent,
                colorOn = GreenOn,
                colorOff = GreenOff,
                colorText = "Verde",
                enablePlay = enablePlay,
                action = Action.PRESS_GREEN_BUTTON,
                onClick =  onClick
            )
            ButtonAction(
                on = action == Action.PRESS_RED_BUTTON && onCurrent,
                colorOn = RedOn,
                colorOff = RedOff,
                colorText = "Rojo",
                enablePlay = enablePlay,
                action = Action.PRESS_RED_BUTTON,
                onClick = onClick

            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_BLUE_BUTTON && onCurrent,
                colorOn = BlueOn,
                colorOff = BlueOff,
                colorText = "Azul",
                enablePlay = enablePlay,
                action = Action.PRESS_BLUE_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_YELLOW_BUTTON && onCurrent,
                colorOn = YellowOn,
                colorOff = YellowOff,
                colorText = "Amarillo",
                enablePlay = enablePlay,
                action = Action.PRESS_YELLOW_BUTTON,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ButtonAction(
    on: Boolean,
    colorOn: Color,
    colorOff: Color,
    colorText: String,
    enablePlay: Boolean,
    action: Action,
    onClick: (Action) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .rotate(45F)
            .border(10.dp, colorOn, RoundedCornerShape(5.dp))
            .size(100.dp)
            .padding(10.dp)
            .background(if (on) colorOn else colorOff)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(action)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = Modifier
            .padding(4.dp)
            .rotate(-45f),
            text = colorText, fontWeight = FontWeight(700), color = Color.White)
    }
}

@Composable
fun StartButton(startGameState: Boolean, onStart: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.height(70.dp).width(100.dp),
            enabled = !startGameState,
            onClick = { onStart() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
            Text(text = "Iniciar",color = Color.Black,fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun DefaultPreview() {
    SimonDiceTheme {
        val dummyAction = Action.NO_ACTION
        SimonGame(0,0,false, dummyAction,dummyAction, false, onClick = {})

    }
}