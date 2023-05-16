package com.lanazirot.simonsays.ui.screens.game

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lanazirot.simonsays.R
import com.lanazirot.simonsays.domain.model.Player
import com.lanazirot.simonsays.domain.model.StepColorAction
import com.lanazirot.simonsays.domain.model.enums.AppStatus
import com.lanazirot.simonsays.domain.model.enums.SimonColorPad
import com.lanazirot.simonsays.presentation.pad.components.CustomDialog
import com.lanazirot.simonsays.presentation.pad_button.components.PadButton
import com.lanazirot.simonsays.ui.common.components.ui.pad.GameStatus
import com.lanazirot.simonsays.ui.common.components.ui.pad.Pad
import com.lanazirot.simonsays.ui.providers.LocalGlobalProvider
import kotlinx.coroutines.delay

@Composable
fun SimonSayGame() {
    val ctx = LocalContext.current
    val gp = LocalGlobalProvider.current
    val padViewModel = gp.padViewModel

    val navController = gp.nav
    val showDialog = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        createNotificationChannel(ctx);
    }

    val appStatus = padViewModel.appStatus.collectAsState()

    LaunchedEffect(appStatus.value) {
        when (appStatus.value) {
            is AppStatus.RUNNING -> {}
            is AppStatus.NOTIFICATION -> {
                //Get data from notification
                val data = (appStatus.value as AppStatus.NOTIFICATION).value
                showSimpleNotification(ctx, "SIMON_SAYS", 1, "New Top!", data as String)
            }
        }
    }


    if (showDialog.value) {
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
            padViewModel.setPlayerToScoreboard(it)
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
            GameStatus.GAME_OVER -> {
                Toast.makeText(ctx, "Game Over", Toast.LENGTH_SHORT).show()
            }
            GameStatus.START -> {}
            GameStatus.PLAYING -> {}
            GameStatus.HOLD -> {}
            GameStatus.PAD_YELLING -> {
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
                            enabled = gameStatus == GameStatus.PLAYING
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
                            enabled = gameStatus == GameStatus.PLAYING
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
                            enabled = gameStatus == GameStatus.PLAYING
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
                            enabled = gameStatus == GameStatus.PLAYING
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
                    padViewModel.gameStart(Player())
                }, enabled = gameStatus == GameStatus.HOLD || gameStatus == GameStatus.GAME_OVER
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

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Simon Says"
        val descriptionText = "Simon Says Game"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("SIMON_SAYS", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}

fun showSimpleNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(androidx.core.R.drawable.notification_bg)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
            return
        }
        notify(notificationId, builder.build())
    }
}