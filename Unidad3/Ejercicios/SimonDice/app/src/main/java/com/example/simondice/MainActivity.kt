package com.example.simondice


import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import com.example.simondice.ui.*
import androidx.compose.runtime.*

import androidx.compose.ui.text.font.FontWeight


import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import android.media.MediaPlayer
import android.media.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.simondice.domain.models.*
import com.example.simondice.domain.models.getrequesttop.Action
import com.example.simondice.models.TopViewModel
import com.example.simondice.models.UiState
import kotlinx.coroutines.delay

import com.example.simondice.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val game = Game()


    private val viewModel : TopViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val blueAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.blue
                    )
                )
            }
            val redAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.red
                    )
                )
            }
            val yellowAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.yellow
                    )
                )
            }
            val greenAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.green
                    )
                )
            }

            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var resultsxState by remember { mutableStateOf<Player?>(Player(0,"",0,0)) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }

            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var topViewModel = hiltViewModel<TopViewModel>()
                    LaunchedEffect(currentActionSimonIndexState) {
                        val action = game.getCurrentAction()
                        println("${action.toString()} = ${game.currentActionSimonIndex}")
                        if (startGameState && !currentActionOn && !game.endSpeak) {
                            when (action) {
                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON -> playAudio(blueAudio)
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
                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON -> playAudio(blueAudio)

                                else -> {
                                    //NO SUENA
                                }
                            }
                            currentActionOn = true
                            delay(300)
                            currentActionOn = false
                            var scorant = game.level

                            if (!game.validateAction(currentActionPlayer)) {
                                println("END GAME")
                                resultsxState = game.end(resultsxState?.name!!)
                                topViewModel.postTop(resultsxState!!)
                                startGameState = game.started
                            }
                            var scoreact = game.level
                            currentActionSimonIndexState = game.currentActionSimonIndex
                            currentActionPlayer = Action.NO_ACTION
                            if (scorant != scoreact) {
                                delay(2000)
                            }

                        }
                    }

                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.Black
                                )
                        ) {
                            Text(
                                text = "SCORE ${game.score}",
                                fontFamily = FontFamily.SansSerif,
                                fontStyle = FontStyle(2),
                                color = Color.White
                            )
                            Text(text = "Simon Dice", fontStyle = FontStyle(1), fontFamily = FontFamily.Monospace, color = Color.White, fontWeight = FontWeight(36))
                            Text(
                                text = "LEVEL ${game.level}",
                                fontFamily = FontFamily.SansSerif,
                                fontStyle = FontStyle(2),
                                color = Color.White
                            )
                            Text(text = "Nombre: ${resultsxState?.name!!}", fontFamily = FontFamily.SansSerif, fontStyle = FontStyle(2), color = Color.White)
                        }
                        SimonGame(
                            startPlayState,
                            game.getCurrentAction(),
                            actionPlayer = currentActionPlayer,
                            currentActionOn
                        ) {
                            currentActionPlayer = it


                        }
                        Column(modifier = Modifier
                            .background(Color.Black)
                            .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

                            OutlinedTextField(value = resultsxState?.name!!, onValueChange = {
                                resultsxState = resultsxState?.copy(name = it)
                            }, label = { Text(text = "Nombre") }, modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                            )

                            StartButton(startGameState, onStart = {
                                game.start()
                                currentActionSimonIndexState = game.currentActionSimonIndex
                                startGameState = game.started

                            })

                            ComposeDialogDemo()


                            //Text(text = "STATUS GAME $startGameState")
                            //Text(text = "STATUS PLAY GAME $startPlayState")
                            //Text(text = "SPEAK ${game.endSpeak}")
                            //Text(text = "SCORE ${game.score}")
                            //Text(text = "LEVEL ${game.level}")
                            //Text(text = "ACTION SIMON ${game.getCurrentAction().toString()}")
                            //Text(text = "ACTION ${currentActionPlayer.toString()}")
                            //Text(text = "ACTION ON/OFF ${currentActionOn.toString()}")
                            //Text(text = "INDEX PLAYER ACTION ON/OFF ${game.currentActionPlayerIndex.toString()}")
                            //Text(text = "INDEX SIMON ON/OFF ${currentActionSimonIndexState.toString()}")
                            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                var apost = game.end(resultsxState?.name!!)
                                startGameState = game.started
                                currentActionSimonIndexState = game.currentActionSimonIndex
                                topViewModel.postTop(apost)

                            }) {
                                Text(text = "RESET")
                            }
                        }
                        if (resultsxState != null) {
                            Row(modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize(), horizontalArrangement = Arrangement.Center){
                            Text(text = "=======RESULTADOS=======")
                            Text(text = resultsxState!!.name)
                            Text(text = resultsxState!!.score.toString())
                            Text(text = resultsxState!!.level.toString())
                            Text(text = "")}
                            var pla : Player = Player(-1,resultsxState!!.name,resultsxState!!.score,resultsxState!!.level)

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

@Composable
fun SimonGame(
    endSpeak: Boolean,
    action: Action,
    actionPlayer: Action,
    offCurrent: Boolean,
    onClick: (action: Action) -> Unit
) {

    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Pad(if (endSpeak) actionPlayer else action, offCurrent, enablePlay = endSpeak, onClick)

    }
}

@Composable
fun Pad(
    action: Action?,
    onCurrent: Boolean,
    enablePlay: Boolean,
    onClick: (action: Action) -> Unit
) {


    Column(
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
                onClick = onClick
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
            .width(175.dp)
            .height(175.dp)
            .rotate(45f)
            .padding(25.dp)
            .border(5.dp, colorOn, RoundedCornerShape(25.dp))
            .background(if (on) colorOn else colorOff, RoundedCornerShape(25.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(action)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = colorText, fontWeight = FontWeight(900), modifier = Modifier.rotate(-45f))
    }
}

@Composable
fun StartButton(startGameState: Boolean, onStart: () -> Unit) {
    Button(
        enabled = !startGameState,
        onClick = { onStart() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Iniciar")
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun DefaultPreview() {
    SimonDiceTheme {
        val dummyAction = Action.PRESS_RED_BUTTON
        SimonGame(false, dummyAction, dummyAction, false, onClick = {})

    }
}