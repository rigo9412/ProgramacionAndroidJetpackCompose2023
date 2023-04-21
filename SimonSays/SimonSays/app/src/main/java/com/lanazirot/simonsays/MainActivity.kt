package com.lanazirot.simonsays

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Score
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.lanazirot.simonsays.domain.model.Player
import com.lanazirot.simonsays.domain.model.SimonColorPad
import com.lanazirot.simonsays.domain.model.StepColorAction
import com.lanazirot.simonsays.presentation.pad.GameStatus.*
import com.lanazirot.simonsays.presentation.pad.PadViewModel
import com.lanazirot.simonsays.presentation.pad.components.CustomDialog
import com.lanazirot.simonsays.presentation.pad.components.Pad
import com.lanazirot.simonsays.presentation.pad_button.components.PadButton
import com.lanazirot.simonsays.presentation.providers.GameProvider
import com.lanazirot.simonsays.presentation.providers.GlobalGameProvider
import com.lanazirot.simonsays.presentation.providers.GlobalProvider
import com.lanazirot.simonsays.presentation.providers.LocalGlobalProvider
import com.lanazirot.simonsays.presentation.scoreboard.components.ScoreboardViewModel
import com.lanazirot.simonsays.ui.navgraph.AppNavGraph
import com.lanazirot.simonsays.ui.theme.SimonSaysTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val padViewModel: PadViewModel by viewModels()
    private val gameViewModel: ScoreboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()
            val gp = GlobalProvider(padViewModel = padViewModel, nav = navController)
            val gameP = GameProvider(currentGame = gameViewModel)

            SimonSaysTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        modifier = Modifier.fillMaxSize(), color = Color.Black
                    ) {
                        CompositionLocalProvider(GlobalGameProvider provides gameP) {
                            Surface(
                                modifier = Modifier.fillMaxSize(), color = Color.Black
                            ) {
                                AppNavGraph(globalProvider = gp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SimonSayGame() {
    val ctx = LocalContext.current
    val gp = LocalGlobalProvider.current
    val padViewModel = gp.padViewModel

    val navController = gp.nav

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
            padViewModel.setName(it)
        }
    }

    val padState = padViewModel.pad.collectAsState()


    LaunchedEffect(padState.value.isGoingToScoreboard) {
        if (padState.value.isGoingToScoreboard) {
            showDialog.value = true
        }
    }


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

    var gameStatus by remember { mutableStateOf(padState.value.gameStatus) }
    var currentColorToFlash by remember { mutableStateOf(SimonColorPad.NONE) }

    LaunchedEffect(padState.value.gameStatus) {
        gameStatus = padState.value.gameStatus
        when (padState.value.gameStatus) {
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
                    playSound(
                        when (step.color) {
                            SimonColorPad.BLUE -> blueAudio
                            SimonColorPad.GREEN -> greenAudio
                            SimonColorPad.RED -> redAudio
                            SimonColorPad.YELLOW -> yellowAudio
                            else -> blueAudio
                        }
                    )
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
            Text(
                text = "Simon Says",
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Pad {
                ConstraintLayout {
                    val (firstRow, secondRow, circle) = createRefs()


                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.constrainAs(firstRow) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                        PadButton(
                            color = if (currentColorToFlash == SimonColorPad.GREEN) SimonColorPad.NONE else SimonColorPad.GREEN,
                            modifier = Modifier
                                .clip(RoundedCornerShape(topStart = 150.dp))
                                .testTag("btn_green"),
                            enabled = gameStatus == PLAYING
                        ) {
                            playSound(greenAudio)
                            padViewModel.compareStep(
                                StepColorAction(
                                    padState.value.currentStep,
                                    SimonColorPad.GREEN
                                )
                            )
                        }
                        PadButton(
                            color = if (currentColorToFlash == SimonColorPad.RED) SimonColorPad.NONE else SimonColorPad.RED,
                            modifier = Modifier
                                .clip(RoundedCornerShape(topEnd = 150.dp))
                                .testTag("btn_red"),
                            enabled = gameStatus == PLAYING
                        ) {
                            playSound(redAudio)
                            padViewModel.compareStep(
                                StepColorAction(
                                    padState.value.currentStep,
                                    SimonColorPad.RED
                                )
                            )
                        }
                    }
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.constrainAs(secondRow) {
                            top.linkTo(firstRow.bottom)
                            bottom.linkTo(parent.bottom)
                        }) {
                        PadButton(
                            color = if (currentColorToFlash == SimonColorPad.YELLOW) SimonColorPad.NONE else SimonColorPad.YELLOW,
                            modifier = Modifier
                                .clip(RoundedCornerShape(bottomStart = 150.dp))
                                .testTag("btn_yellow"),
                            enabled = gameStatus == PLAYING
                        ) {
                            playSound(yellowAudio)
                            padViewModel.compareStep(
                                StepColorAction(
                                    padState.value.currentStep,
                                    SimonColorPad.YELLOW
                                )
                            )
                        }
                        PadButton(
                            color = if (currentColorToFlash == SimonColorPad.BLUE) SimonColorPad.NONE else SimonColorPad.BLUE,
                            modifier = Modifier
                                .clip(RoundedCornerShape(bottomEnd = 150.dp))
                                .testTag("btn_blue"),
                            enabled = gameStatus == PLAYING
                        ) {
                            playSound(blueAudio)
                            padViewModel.compareStep(
                                StepColorAction(
                                    padState.value.currentStep,
                                    SimonColorPad.BLUE
                                )
                            )
                        }
                    }

                    Canvas(modifier = Modifier
                        .size(125.dp)
                        .clickable(onClick = {})
                        .constrainAs(circle) {
                            top.linkTo(firstRow.top)
                            bottom.linkTo(secondRow.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }, onDraw = {
                        drawCircle(color = Color.Black)
                    })
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            ) {
                Text(
                    text = "Score: ${padState.value.player?.score ?: 0}",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .height(100.dp)
                    .width(250.dp)
                    .shadow(10.dp)
                    .clip(RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp))
                    .testTag("btn_start"),
                onClick = {
                    padViewModel.gameStart(Player("Alan"))
                }, enabled = gameStatus == HOLD || gameStatus == GAME_OVER
            ) {
                Icon(Icons.Rounded.PlayArrow, contentDescription = "Localized description")
                Text(
                    text = "Start Game",
                    fontFamily = MaterialTheme.typography.h1.fontFamily,
                    fontSize = 30.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .height(100.dp)
                    .width(250.dp)
                    .shadow(10.dp)
                    .clip(RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp))
                    .testTag("btn_scoreboard"),
                onClick = {
                   navController.navigate("scoreboard")
                }
            ) {
                Icon(Icons.Rounded.Score, contentDescription = "Localized description")
                Text(
                    text = "Scoreboard",
                    fontFamily = MaterialTheme.typography.h1.fontFamily,
                    fontSize = 30.sp
                )
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

