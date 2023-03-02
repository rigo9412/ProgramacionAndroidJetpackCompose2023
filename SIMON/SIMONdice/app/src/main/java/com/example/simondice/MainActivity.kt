package com.example.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simondice.modelos.*
import com.example.simondice.ui.theme.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.tooling.preview.Devices
import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.lightColors
import androidx.compose.ui.draw.clip
import com.example.simondice.ui.theme.SIMONdiceTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    private val game = Game()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val blueAudio: MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(this, R.raw.classic_hurt)) }
            val redAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.minecraft_click)) }
            val yellowAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.nom_nom)) }
            val greenAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.villager_sound)) }

            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var resultsxState by remember { mutableStateOf<Player?>(null) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }

            SIMONdiceTheme {
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
                            println("END GAME")
                            resultsxState = game.end("dummy")
                            startGameState = game.started
                        }

                        currentActionSimonIndexState = game.currentActionSimonIndex
                        currentActionPlayer = Action.NO_ACTION

                    }
                }

                Column(modifier = Modifier.background(Color.Black).fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
                    Text(color = Color.White ,text = "SCORE ${game.score}")
                    Text(color = Color.White ,text = "LEVEL ${game.level}")
                    SimonGame(
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

                    })

                    //Text(text = "STATUS GAME $startGameState")
                    //Text(text = "STATUS PLAY GAME $startPlayState")
                    //Text(text = "SPEAK ${game.endSpeak}")

                    //Text(text = "ACTION SIMON ${game.getCurrentAction().toString()}")
                    //Text(text = "ACTION ${currentActionPlayer.toString()}")
                    //Text(text = "ACTION ON/OFF ${currentActionOn.toString()}")
                    //Text(text = "INDEX PLAYER ACTION ON/OFF ${game.currentActionPlayerIndex.toString()}")
                    //Text(text = "INDEX SIMON ON/OFF ${currentActionSimonIndexState.toString()}")
                    Button(onClick = {
                        game.end("dummy")
                        startGameState = game.started
                        currentActionSimonIndexState = game.currentActionSimonIndex

                    }) {
                        Text(color = Color.White ,text = "RESET")
                    }

                    if(resultsxState != null) {
                        Text(color = Color.White ,text = "=======RESULTADOS=======")
                        //Text(text = resultsxState!!.name)
                        Text(color = Color.White ,text = resultsxState!!.score.toString())
                        Text(color = Color.White ,text = resultsxState!!.level.toString())
                    }
                }
            }
        }
    }
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
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun DefaultPreview() {
    SIMONdiceTheme() {
        val dummyAction = Action.PRESS_RED_BUTTON

        SimonGame(false, dummyAction,dummyAction, false, onClick = {})

    }
}
    @Composable
    fun SimonGame(endSpeak: Boolean, action: Action,actionPlayer:Action, offCurrent: Boolean, onClick: (action: Action) -> Unit) {

        Column(
            modifier = Modifier.background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Pad(if(endSpeak) actionPlayer else action, offCurrent,enablePlay = endSpeak,onClick)

        }
    }
@Composable
fun Pad(action: Action?, onCurrent: Boolean, enablePlay: Boolean,onClick: (action: Action) -> Unit) {



    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()

    ) {
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
            .width(100.dp)
            .clip(shape = RoundedCornerShape(percent = 50))
            .height(100.dp)
            .padding(4.dp)
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
        //Text(text = colorText, fontWeight = FontWeight(900))
    }
}

@Composable
fun StartButton(startGameState: Boolean, onStart: () -> Unit) {
    Button(
        enabled = !startGameState,
        onClick = { onStart() }) {
        Text(text = "Iniciar")
    }
}