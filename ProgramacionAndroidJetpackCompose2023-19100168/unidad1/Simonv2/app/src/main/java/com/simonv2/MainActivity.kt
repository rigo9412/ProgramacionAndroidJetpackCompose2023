package com.simonv2

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simonv2.domain.model.Action
import com.simonv2.domain.model.Game
import com.simonv2.domain.model.Player
import com.simonv2.ui.theme.GreenOff
import com.simonv2.ui.theme.*
import com.simonv2.ui.theme.Simonv2Theme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    private val game = Game()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val blueAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.pickupcoindos
                    )
                )
            }
            val redAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.click
                    )
                )
            }
            val yellowAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.pickupcoin
                    )
                )
            }
            val powerUp: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.powerup
                    )
                )
            }
            val greenAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.greena
                    )
                )
            }


            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var resultsState by remember { mutableStateOf<Player?>(null) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }

            Simonv2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
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
                                }
                            }
                            currentActionOn = true
                            delay(1000)
                            currentActionOn = false
                            game.moveToNextAction()
                            currentActionSimonIndexState = game.currentActionSimonIndex
                            when (action) {
                                Action.PRESS_GREEN_BUTTON -> pauseAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> pauseAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> pauseAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON -> pauseAudio(blueAudio)
                                else -> {
                                    //NO SUENA
                                }
                            }

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

                            if (!game.validateAction(currentActionPlayer)) {
                                println("END GAME")
                                resultsState = game.end("dummy")
                                startGameState = game.started
                            }

                            currentActionSimonIndexState = game.currentActionSimonIndex
                            when (currentActionPlayer) {
                                Action.PRESS_GREEN_BUTTON -> pauseAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> pauseAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> pauseAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON -> pauseAudio(blueAudio)
                                else -> {
                                }
                            }
                            currentActionPlayer = Action.NO_ACTION


                        }
                    }


                    Scaffold(
                        content = { content() },
                        topBar = { FBC() }
                        )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .fillMaxWidth()
                        )
                        SimonGame(
                            game = game,
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


                        if (!game.started) {
                            CustomButton("INICIAR", startGameState, onClick = {
                                game.start()
                                currentActionSimonIndexState = game.currentActionSimonIndex
                                startGameState = game.started
                                resultsState = null

                            })
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun FBC() {
    TopAppBar(
        title = { Text("Simon Say") }
        //horizontalAligment = Alignment.CenterHorizontally
    )
}

@Composable
fun content() {

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
fun SimonGame(
    game: Game,
    results: Player?,
    actionPlayer: Action,
    onCurrent: Boolean,
    onClick: (action: Action) -> Unit
) {
    Column(

        modifier = Modifier.background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Status(game, results)
        Score(game, results)
        Pad(
            if (game.endSpeak) actionPlayer else game.getCurrentAction(),
            onCurrent,
            enablePlay = game.endSpeak,
            onClick
        )

    }
}


@Composable
fun Status(game: Game, results: Player?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TitleShadow()
        if (game.started && results == null) {
            Status(status = "JUEGO INICIADO")
        } else if (results != null) {
            Status(status = "JUEGO TERMINADO")
        }
    }
}


@Composable
fun Score(game: Game, player: Player?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (player != null) {
            Text(
                text = "Puntaje: ${player.score}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "Nivel: ${player.level}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        } else {
            Text(
                text = "Puntaje: ${game.score}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "Nivel: ${game.level}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        }

    }
}

@Composable
fun Pad(
    action: Action?, onCurrent: Boolean, enablePlay: Boolean, onClick: (action: Action) -> Unit
) {


    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_GREEN_BUTTON && onCurrent,
                colorOn = GreenOn,
                colorOff = GreenOff,
                enablePlay = enablePlay,
                action = Action.PRESS_GREEN_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_RED_BUTTON && onCurrent,
                colorOn = RedOn,
                colorOff = RedOff,
                enablePlay = enablePlay,
                action = Action.PRESS_RED_BUTTON,
                onClick = onClick

            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_BLUE_BUTTON && onCurrent,
                colorOn = BlueOn,
                colorOff = BlueOff,
                enablePlay = enablePlay,
                action = Action.PRESS_BLUE_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_YELLOW_BUTTON && onCurrent,
                colorOn = YellowOn,
                colorOff = YellowOff,
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
    enablePlay: Boolean,
    action: Action,
    onClick: (Action) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .padding(6.dp)
            .border(10.dp, colorOn, RoundedCornerShape(5.dp))
            .size(150.dp)
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

    }
}


@Composable
fun CustomButton(text: String, startGameState: Boolean, onClick: () -> Unit) {
    TextButton(modifier = Modifier
        .padding(10.dp)
        .height(80.dp)
        .fillMaxWidth(),
        border = BorderStroke(
            width = 2.dp, brush = Brush.horizontalGradient(
                listOf(
                    Color.Cyan, Color.Yellow
                )
            )
        ),

        enabled = !startGameState,
        onClick = { onClick() }) {
        Text(text = text, color = Color.White, fontSize = 28.sp)
    }
}

@Composable
fun TitleShadow() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "SIMON DICE", color = Color.Black, style = TextStyle(
            fontSize = 30.sp
        )
    )
}


@Composable
fun Status(status: String) {
    Text(
        modifier = Modifier.background(Color.Black),
        text = status,
        color = Color.White,
        fontSize = 24.sp

    )
}


@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun DefaultPreview() {
    Simonv2Theme {
        val dummyAction = Action.PRESS_RED_BUTTON
        val game = Game()
        SimonGame(game, null, dummyAction, false, onClick = {})

    }
}

