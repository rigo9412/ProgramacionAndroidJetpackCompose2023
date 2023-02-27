package com.lanazirot.simonsays

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lanazirot.simonsays.domain.model.Player
import com.lanazirot.simonsays.domain.model.SimonColorPad
import com.lanazirot.simonsays.domain.model.StepColorAction
import com.lanazirot.simonsays.presentation.pad.GameStatus.*
import com.lanazirot.simonsays.presentation.pad.PadViewModel
import com.lanazirot.simonsays.presentation.pad.components.Pad
import com.lanazirot.simonsays.presentation.pad_button.components.PadButton
import com.lanazirot.simonsays.ui.theme.SimonSaysTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimonSayGame()
        }
    }
}

@Composable
fun SimonSayGame(padViewModel: PadViewModel = viewModel()) {
    val padState = padViewModel.pad.collectAsState()
    SimonSaysTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = Color.Black
        ) {
            val ctx = LocalContext.current

           val blueAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        ctx,
                        R.raw.blue
                    )
                )
            }

            val greenAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        ctx,
                        R.raw.green
                    )
                )
            }

            val redAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        ctx,
                        R.raw.red
                    )
                )
            }

            val yellowAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        ctx,
                        R.raw.yellow
                    )
                )
            }

            //remember game status
            var gameStatus by remember { mutableStateOf(padState.value.gameStatus) }
            var currentColorToFlash by remember { mutableStateOf(SimonColorPad.NONE) }

            LaunchedEffect(padState.value.gameStatus) {
                gameStatus = padState.value.gameStatus
                when(padState.value.gameStatus) {
                    GAME_OVER -> {
                        Toast.makeText(ctx, "Game Over", Toast.LENGTH_SHORT).show()
                    }
                    START -> {}
                    PLAYING -> {}
                    HOLD -> {}
                    PAD_YELLING -> {
                        delay(500)
                        for (step in padState.value.pad?.colorSequence!!) {
                            currentColorToFlash = step.color
                            playSound(when(step.color) {
                                SimonColorPad.BLUE -> blueAudio
                                SimonColorPad.GREEN -> greenAudio
                                SimonColorPad.RED -> redAudio
                                SimonColorPad.YELLOW -> yellowAudio
                                else -> blueAudio
                            })
                            delay(500)
                            currentColorToFlash = SimonColorPad.NONE
                            delay(500)
                        }
                        delay(500)
                        padViewModel.gamePlaying()
                    }
                }
            }



            Column(
                modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Pad {
                        Row(horizontalArrangement = Arrangement.Center) {
                            PadButton(
                                color = if(currentColorToFlash == SimonColorPad.GREEN) SimonColorPad.NONE else SimonColorPad.GREEN,
                                modifier = Modifier,
                                enabled = gameStatus == PLAYING
                            ) {
                                playSound(greenAudio)
                                padViewModel.compareStep(StepColorAction(padState.value.currentStep, SimonColorPad.GREEN))
                            }
                            PadButton(
                                color = if (currentColorToFlash == SimonColorPad.RED) SimonColorPad.NONE else SimonColorPad.RED,
                                modifier = Modifier,
                                enabled = gameStatus == PLAYING
                            ) {
                                playSound(redAudio)
                                padViewModel.compareStep(StepColorAction(padState.value.currentStep, SimonColorPad.RED))
                            }
                        }
                        Row(horizontalArrangement = Arrangement.Center) {
                            PadButton(
                                color = if (currentColorToFlash == SimonColorPad.YELLOW) SimonColorPad.NONE else SimonColorPad.YELLOW,
                                modifier = Modifier,
                                enabled = gameStatus == PLAYING
                            ) {
                                playSound(yellowAudio)
                                padViewModel.compareStep(StepColorAction(padState.value.currentStep, SimonColorPad.YELLOW))
                            }
                            PadButton(
                                color = if (currentColorToFlash == SimonColorPad.BLUE) SimonColorPad.NONE else SimonColorPad.BLUE,
                                modifier = Modifier,
                                enabled = gameStatus == PLAYING
                            ) {
                                playSound(blueAudio)
                                padViewModel.compareStep(StepColorAction(padState.value.currentStep, SimonColorPad.BLUE))
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(200.dp)
                            .background(color = MaterialTheme.colors.primary)
                    ) {
                        Text(
                            text = "Score: ${padState.value.player?.score ?: 0}",
                            color = MaterialTheme.colors.onPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        padViewModel.gameStart(Player("Alan"))
                    }, enabled = gameStatus == HOLD  || gameStatus == GAME_OVER) {
                        Text(
                            text = "Start Game", fontFamily = MaterialTheme.typography.h1.fontFamily
                        )
                    }
                }
            }
        }
    }
}

fun playSound(mediaPlayer: MediaPlayer) {
    if (!mediaPlayer.isPlaying) {
        mediaPlayer.start()
    } else {
        mediaPlayer.pause()
        mediaPlayer.seekTo(0)
        mediaPlayer.start()
    }
}

@Preview(name = "Pad", showBackground = true)
@Composable
fun PreviewGreeting() {
    SimonSayGame()
}