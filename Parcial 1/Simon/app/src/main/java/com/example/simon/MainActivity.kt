package com.example.simon

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simon.models.Action
import com.example.simon.models.Game
import com.example.simon.models.Player
import com.example.simon.ui.theme.*
import kotlinx.coroutines.delay

val Cyan500 = Color(0xFF06A898)

class MainActivity : ComponentActivity() {
    private val game = Game()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val azulaudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.azulaudio)) }
            val rojoaudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.rojoaudio)) }
            val amarilloaudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.amarilloaudio)) }
            val verdeaudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.verdeaudio)) }

            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var resultsState by remember { mutableStateOf<Player?>(null) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }

            SimonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(currentActionSimonIndexState) {
                        val action = game.getCurrentAction()
                        println("${action.toString()} = ${game.currentActionSimonIndex}")
                        if (startGameState && !currentActionOn && !game.endSpeak) {
                            when (action) {
                                Action.CLICK_GREEN_BUTTON -> playAudio(verdeaudio)
                                Action.CLICK_RED_BUTTON -> playAudio(rojoaudio)
                                Action.CLICK_YELLOW_BUTTON -> playAudio(amarilloaudio)
                                Action.CLICK_BLUE_BUTTON -> playAudio(azulaudio)
                                else -> { }
                            }
                            currentActionOn = true
                            delay(1000)
                            currentActionOn = false
                            game.moveToNextAction()
                            currentActionSimonIndexState = game.currentActionSimonIndex
                            when (action) {
                                Action.CLICK_GREEN_BUTTON -> pauseAudio(verdeaudio)
                                Action.CLICK_RED_BUTTON -> pauseAudio(rojoaudio)
                                Action.CLICK_YELLOW_BUTTON -> pauseAudio(amarilloaudio)
                                Action.CLICK_BLUE_BUTTON -> pauseAudio(azulaudio)
                                else -> { }
                            }
                        }
                        startPlayState = game.endSpeak
                    }

                    LaunchedEffect(currentActionPlayer) {
                        if (startGameState && game.endSpeak && currentActionPlayer != Action.NO_ACTION && !currentActionOn) {
                            when (currentActionPlayer) {
                                Action.CLICK_GREEN_BUTTON -> playAudio(verdeaudio)
                                Action.CLICK_RED_BUTTON -> playAudio(rojoaudio)
                                Action.CLICK_YELLOW_BUTTON -> playAudio(amarilloaudio)
                                Action.CLICK_BLUE_BUTTON -> playAudio(azulaudio)
                                else -> { }
                            }
                            currentActionOn = true
                            delay(300)
                            currentActionOn = false
                            if (!game.validateAction(currentActionPlayer)) {
                                resultsState = game.end("Placeholder")
                                startGameState = game.started
                            }
                            currentActionSimonIndexState = game.currentActionSimonIndex
                            when (currentActionPlayer) {
                                Action.CLICK_GREEN_BUTTON -> pauseAudio(verdeaudio)
                                Action.CLICK_RED_BUTTON -> pauseAudio(rojoaudio)
                                Action.CLICK_YELLOW_BUTTON -> pauseAudio(amarilloaudio)
                                Action.CLICK_BLUE_BUTTON -> pauseAudio(azulaudio)
                                else -> { }
                            }
                            currentActionPlayer = Action.NO_ACTION
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize().background(Cyan500)
                    ) {
                        Simon(
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
                        if(!game.started) {
                            CustomButton("START GAME", startGameState, onClick = {
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
fun Simon(game: Game, results: Player?, actionPlayer: Action, onCurrent: Boolean, onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier.background(Cyan500),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Status(game, results)
        Score(game,results)
        Pad(if (game.endSpeak) actionPlayer else game.getCurrentAction(), onCurrent, enablePlay = game.endSpeak, onClick)
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
            Status(status = "START")
        } else if (results != null) {
            Status(status = "GAME OVER")
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
        if(player != null){
            Text(
                text = "SCORE: ${player.score}", color = Color.LightGray, fontSize = 30.sp,
                fontFamily = FontFamily.Default, fontWeight = FontWeight(900))
            Text(
                text = "LEVEL: ${player.level}", color = Color.LightGray, fontSize = 30.sp,
                fontFamily = FontFamily.Default, fontWeight = FontWeight(900))
        }else{
            Text(
                text = "SCORE: ${game.score}", color = Color.LightGray, fontSize = 30.sp,
                fontFamily = FontFamily.Default, fontWeight = FontWeight(900))
            Text(
                text = "LEVEL: ${game.level}", color = Color.LightGray, fontSize = 30.sp,
                fontFamily = FontFamily.Default, fontWeight = FontWeight(900))
        }
    }
}

@Composable
fun Pad(action: Action?, onCurrent: Boolean , enablePlay: Boolean, onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            ButtonAction(
                on = action == Action.CLICK_GREEN_BUTTON && onCurrent, colorOn = VerdeOn, colorOff = VerdeOff,
                colorText = "VERDE", enablePlay = enablePlay, action = Action.CLICK_GREEN_BUTTON, onClick =  onClick
            )
            ButtonAction(
                on = action == Action.CLICK_RED_BUTTON && onCurrent, colorOn = RojoOn, colorOff = RojoOff,
                colorText = "ROJO", enablePlay = enablePlay, action = Action.CLICK_RED_BUTTON, onClick = onClick
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.CLICK_BLUE_BUTTON && onCurrent, colorOn = AzulOn, colorOff = AzulOff,
                colorText = "AZUL", enablePlay = enablePlay, action = Action.CLICK_BLUE_BUTTON, onClick = onClick
            )
            ButtonAction(
                on = action == Action.CLICK_YELLOW_BUTTON && onCurrent, colorOn = AmarilloOn, colorOff = AmarilloOff,
                colorText = "AMARILLO", enablePlay = enablePlay, action = Action.CLICK_YELLOW_BUTTON, onClick = onClick
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
            .width(200.dp)
            .height(200.dp)
            .padding(4.dp)
            .border(4.dp,colorOn, shape = buttonShape)
            .background(if (on) colorOn else colorOff)
            .shadow(
                elevation = 30.dp,
                shape = buttonShape,
                ambientColor = Color.White,
                spotColor = Color.White
            )
            .background(
                if (on) colorOn else colorOff, buttonShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(action)
            }, contentAlignment = Alignment.Center
    ) {
        Text(text = colorText, fontWeight = FontWeight(900), fontSize = 30.sp, color = Color.White)
    }
}


@Composable
fun CustomButton(text: String, startGameState: Boolean, onClick: () -> Unit) {
    TextButton(
        modifier = Modifier
            .padding(25.dp)
            .height(100.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 5.dp,
            brush = Brush.verticalGradient(
                listOf(
                    Color.Green,
                    Color.Yellow,
                    Color.Red
                )
            )
        ),
        enabled = !startGameState,
        onClick = { onClick() }) {
        Text(text = text, color = Color.Black, fontFamily = FontFamily.SansSerif, fontSize = 42.sp)
    }
}

@Composable
fun TitleShadow() {
    val offset = Offset(10.0f, 15.0f)
    Text(
        text = "SIMON",
        color = Color.White,
        style = TextStyle(
            fontSize = 100.sp,
            fontFamily = FontFamily.Cursive,
            shadow = Shadow(
                color = Color.DarkGray,
                offset = offset,
                blurRadius = 7f
            )
        )
    )
}

@Composable
fun Status(status: String) {
    Text(
        modifier = Modifier.background(Color.DarkGray), text = status, color = Color.White, fontSize = 40.sp)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimonTheme {
        val placeholderAction = Action.CLICK_RED_BUTTON
        val game = Game()
        Simon(game, null, placeholderAction,false, onClick = {})
    }
}