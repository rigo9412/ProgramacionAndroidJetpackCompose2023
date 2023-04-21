package com.example.simondice

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.model.*
import com.example.simondice.ui.theme.*
import kotlinx.coroutines.delay


val scoreMap = mutableMapOf<String, Int>()
var numero = 0
class MainActivity : ComponentActivity() {
    private val game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            val blueAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this, R.raw.blue
                    )
                )
            }
            val redAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this, R.raw.red
                    )
                )
            }
            val yellowAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this, R.raw.yellow
                    )
                )
            }
            val greenAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this, R.raw.green
                    )
                )
            }
            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var resultsState by remember { mutableStateOf<Player?>(null) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }


            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
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
                                    //NO SUENA
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
                                    //NO SUENA
                                }
                            }
                            currentActionPlayer = Action.NO_ACTION


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
                            CustomButton("INICIAR JUEGO", startGameState, onClick = {
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
fun SimonGame(
    game: Game,
    results: Player?,
    actionPlayer: Action,
    onCurrent: Boolean,
    onClick: (action: Action) -> Unit
) {

    val (isScoreModalOpen, setIsScoreModalOpen) = remember { mutableStateOf(false) }
    fun openScoreModal() {
        setIsScoreModalOpen(true)
    }

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
    Column(
        modifier = Modifier.background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Button(onClick = { openScoreModal() }) {
            Text("Puntuación")
        }

    }

    if (isScoreModalOpen) {
        AlertDialog(
            onDismissRequest = { setIsScoreModalOpen(false) },
            title = { Text("Top 10 Puntaje ") },
            text = {

                Top10Scores(results,scoreMap)
            },
            confirmButton = {
                Button(onClick = { setIsScoreModalOpen(false) }) {
                    Text("Cerrar")
                }
            }
        )
    }


}


@SuppressLint("UnrememberedMutableState")
@Composable
fun Status(game: Game, results: Player?) {
    val isDialogOpen = remember { mutableStateOf(true) }
    var playername =  remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TitleShadow()
        if (game.started && results == null) {
            Status(status = "JUEGO INICIADO")
            isDialogOpen.value = true
        } else if (results != null) {

            Status(status = "JUEGO TERMINADO")
            numero= numero +1

           if(isDialogOpen.value){
               AlertDialog(
                   onDismissRequest = { isDialogOpen.value=false },
                   title = { Text("Top 10 Puntaje ") },
                   text = {

                       RegistrarScore(results = results)
                   },
                   confirmButton = {
                       Column {
                           TextField(
                               value =playername.value,
                               onValueChange = {playername.value = it}
                           )
                           Button(
                               onClick = {
                                   scoreMap.put(playername.value,results.score)
                                   isDialogOpen.value=false
                               },
                               modifier = Modifier
                                   .padding(10.dp)
                                   .height(60.dp)
                           ) {
                               Text(text = "Registrar")
                           }
                       }
                   })
           }

        }
    }
}

@Composable
fun RegistrarScore(results: Player){

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(19.dp, 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Registra tu score",
                fontSize = 50.sp
            )
        }

    }
}
@Composable
fun Score(game: Game, player: Player?,) {
    //val scoreMap = mutableMapOf<String, Int>()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (player != null) {


            //Top10Scores(scoreMap)
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
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Top10Scores(player: Player?, scores: MutableMap<String,Int>) {


    val topScores = scores.entries
        .distinctBy { it.value }
        .toList()
        .sortedByDescending { it.value }

        .take(10)
    LazyColumn {
        itemsIndexed(topScores) { index, score ->
            ListItem(
                text = {
                    Text(
                        text = "${index + 1}. ${score.key}",
                        fontWeight = FontWeight.Bold
                    )
                },
                secondaryText = {
                    Text(text = "${score.value}")
                }
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
                colorText = "Verde",
                enablePlay = enablePlay,
                rotate = 135f,
                action = Action.PRESS_GREEN_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_RED_BUTTON && onCurrent,
                colorOn = RedOn,
                colorOff = RedOff,
                colorText = "Rojo",
                rotate = -135f,
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
                colorText = "Azul",
                enablePlay = enablePlay,
                rotate = 45f,
                action = Action.PRESS_BLUE_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_YELLOW_BUTTON && onCurrent,
                colorOn = YellowOn,
                colorOff = YellowOff,
                colorText = "Amarillo",
                enablePlay = enablePlay,
                rotate = -45f,
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
    rotate: Float,
    action: Action,
    onClick: (Action) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(

        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(4.dp)
            .rotate(rotate)
            .border(4.dp, colorOn, shape = buttonShape)
//            .background(if (on) colorOn else colorOff)
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
//        Text(text = colorText, fontWeight = FontWeight(900), color = Color.White)
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
        text = "SIMON DICE", color = Color.White, style = TextStyle(
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
    SimonDiceTheme {
        val dummyAction = Action.PRESS_RED_BUTTON
        val game = Game()
        SimonGame(game, null, dummyAction, false, onClick = {})

    }
}