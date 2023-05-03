package com.ezequiel.simondice

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ezequiel.simondice.domain.modelo.getTop.Action
import com.ezequiel.simondice.domain.modelo.Game
import com.ezequiel.simondice.domain.modelo.Player
import com.ezequiel.simondice.modelo.TopViewModel
import com.ezequiel.simondice.models.Top10
import com.ezequiel.simondice.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


//public var player = (Player(Nombre = "", Nivel = 0, Puntos = 0))
//public var top10Pri = Top10()
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val blueAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.azul
                    )
                )
            }
            val redAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.rojo
                    )
                )
            }
            val yellowAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.amarillo
                    )
                )
            }
            val greenAudio: MediaPlayer by remember {
                mutableStateOf(
                    MediaPlayer.create(
                        this,
                        R.raw.verde
                    )
                )
            }
            //val startAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.start))    }
            //Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/adelia.ttf")

            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var resultsxState by remember { mutableStateOf<Player?>(Player(0,"Prueba",0,0)) }
            var currentActionPlayerIndexState by remember { mutableStateOf(game.currentActionPlayerIndex) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }
            var openDialog by remember { mutableStateOf(game.endGame) }

            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val topViewModel = hiltViewModel<TopViewModel>()
                    LaunchedEffect(currentActionSimonIndexState) {
                        if (startGameState && !currentActionOn) {
                            when (game.getCurrentAction()) {
                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON -> playAudio(blueAudio)
                                //Action.PRESS_INICIO_BUTTON -> playAudio(startAudio)

                                else -> {
                                    //NO SUENA
                                }
                            }

                            currentActionOn = true
                            delay(1000)
                            currentActionOn = false
                            game.moveToNextAction()
                            currentActionSimonIndexState = game.currentActionSimonIndex
                            //currentActionOn = false
                        }
                        startPlayState = game.endSpeak
                    }

//                    Column() {
//                        SimonGame(
//                            game.level,
//                            game.score,
//                            startPlayState,
//                            game.getCurrentAction(),
//                            actionPlayer = currentActionPlayer,
//                            currentActionOn) {
//                            currentActionPlayer = it
//                        }
                    LaunchedEffect(currentActionPlayer) {
                        if (startGameState && game.endSpeak && currentActionPlayer != Action.NO_ACTION && !currentActionOn) {
                            when (currentActionPlayer) {
                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON -> playAudio(blueAudio)
                                //Action.PRESS_INICIO_BUTTON -> playAudio(startAudio)

                                else -> {
                                    //NO SUENA
                                }
                            }
                            currentActionOn = true
                            delay(300)
                            currentActionOn = false

                            if (!game.validateAction(currentActionPlayer)) {
                                println("END GAME")
                                resultsxState = game.end(resultsxState?.name!!)
                                topViewModel.postTop(resultsxState!!)
                               // top10Pri.add(resultsxState!!)
                                openDialog = true
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
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    ) {
                        SimonGame(
                            //player.Nombre,
                            game.level,
                            game.score,
                            startPlayState,
                            game.getCurrentAction(),
                            actionPlayer = currentActionPlayer,
                            currentActionOn
                        ) {
                            currentActionPlayer = it
                        }
 //                       Text(text = "Nombre: ${resultsxState?.name!!}", fontFamily = FontFamily.SansSerif, fontStyle = FontStyle(2), color = Color.White)
//                        Spacer(
//                            modifier = Modifier
//                                .height(52.dp)
//                                .fillMaxWidth()
//                        )
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
                        if (!game.started) {
                            CustomButton("S t a r t", startGameState, onClick = {
                                game.start()
                                currentActionSimonIndexState = game.currentActionSimonIndex
                                startGameState = game.started
                                //resultsState = null

                            })
                        }

//                        Button(onClick = {
//                            game.end("dummy")
//                            startGameState = game.started
//                            currentActionSimonIndexState = game.currentActionSimonIndex
//
//                        }) {
//                            Text(text = "RESET")
//                        }
                    }
                    var bolfull by remember { mutableStateOf( false)}
                    Column() {
                        AlertDialog(activar = openDialog, onConfirm = {
                            openDialog = false
                            bolfull = true
                        }, onDismiss = {
                            openDialog = false
                        })
                        if (bolfull) {
                            ComposeDialogDemo()
                        }
                        else {
                            bolfull = false
                        }
                    }

//                    if (bolfull) {
//                        SimonFullDetailDialog(player = player, isVisible = bolfull)
//                    }

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
    //nombre: String,
    level: Int,
    score: Int,
    endSpeak: Boolean,
    action: Action,
    actionPlayer: Action,
    offCurrent: Boolean,
    onClick: (action: Action) -> Unit
) {
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Pad(
            //player.Nombre,
            level,
            score,
            if (endSpeak) actionPlayer else action,
            offCurrent,
            enablePlay = endSpeak,
            onClick
        )
    }
}

@Composable
fun Pad(
    //nombre: String,
    level: Int,
    score: Int,
    action: Action?,
    onCurrent: Boolean,
    enablePlay: Boolean,
    onClick: (action: Action) -> Unit
) {
//    var jugador by remember {
//        mutableStateOf(player.copy())
//    }
    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val offset = Offset(5.0f, 10.0f)
            Text(
                text = "¡Simón Dice!",
                fontWeight = FontWeight(900),
                color = Color.White,
                fontFamily = FontFamily.Cursive,
                style = TextStyle(
                    fontSize = 50.sp,
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = offset,
                        blurRadius = 3f
                    )
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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
                onClick = onClick,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val game = Game()
            Text(
                text = "Nivel $level    Score $score",
                fontWeight = FontWeight(900),
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                style = TextStyle(fontSize = 20.sp)
            )
        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.Transparent)
//                .height(50.dp)
//        ) {
//            val game = Game()
//            Text(text = "Nombre: ${resultsxState?.name!!}", fontFamily = FontFamily.SansSerif, fontStyle = FontStyle(2), color = Color.White)
            //Text(text = "Nombre", fontWeight = FontWeight(900) , color = Color.White, fontFamily = FontFamily.Monospace, style = TextStyle(fontSize = 20.sp))
//            TextField(value = jugador.Nombre, onValueChange = {
//                jugador = jugador.copy(Nombre = it)
//                player = jugador.copy()
//                var pa = Player(Nombre= it,Puntos = player.Puntos, Nivel = player.Nivel)
//                pa.Nivel = player.Nivel
//                pa.Puntos = player.Puntos
//                pa.Nombre = it
//                player = pa
//            })
//        }
    }
}

@Composable
fun CustomButton(text: String, startGameState: Boolean, onClick: () -> Unit) {
    TextButton(
        modifier = Modifier
            .padding(0.dp)
            .height(80.dp)
            .background(color = Color.Transparent)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 2.dp,
            brush = Brush.horizontalGradient(
                listOf(Color.Magenta, Color.Cyan)
            )
        ),
        enabled = !startGameState,
        onClick = { onClick() }) {
        Text(text = text, color = Color.White, fontFamily = FontFamily.Monospace, fontSize = 28.sp)
    }
}

@Composable
fun CustomInput(
    label: String,
    value: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier

) {

    Column() {
        OutlinedTextField(
            value = value,
            onValueChange = onChangeValue,
            modifier = modifier,

            label = { Text(label) },
            maxLines = 1,
            singleLine = true,
        )
    }
}


@Composable
fun AlertDialog(
    activar: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    MaterialTheme {
        Column {
            var openDialog by remember { mutableStateOf(false) }
            var ba = false

            if (activar) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                        onDismiss()
                    },
                    title = {
                        Text(text = "")
                    },
                    text = {
                        Text(
                            text = "¡Fin del juego!",
                            fontWeight = FontWeight(900),
                            color = Color.Black,
                            fontFamily = FontFamily.Cursive,
                            style = TextStyle(
                                fontSize = 50.sp,
                                shadow = Shadow(
                                    color = Color.DarkGray,
                                    blurRadius = 3f
                                )
                            )
                        )
                    },
                    dismissButton = {
                        Button(modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                            border = BorderStroke(
                                width = 2.dp,
                                brush = Brush.horizontalGradient(
                                    listOf(Color.Magenta, Color.Cyan)
                                )
                            ),
                            onClick = {
                                openDialog = false
                                onDismiss()

                            }) {
                            Text("Jugar de nuevo")
                        }
                    },
                    confirmButton = {
                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                brush = Brush.horizontalGradient(
                                    listOf(Color.Magenta, Color.Cyan)
                                )
                            ),
                            onClick = {
                                onConfirm()
                                openDialog = false
                            }) {
                            //Text("Ver mejores puntajes")
                            ComposeDialogDemo()
                        }
                    }
                )
            }
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
            //.rotate(45F)
            .border(200.dp, if (on) colorOn else colorOff, RoundedCornerShape(70.dp))
            .width(150.dp)
            .height(150.dp)
            .padding(4.dp)

            //.background(Color.White)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (!on && enablePlay) {
                    onClick(action)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp),
            //.rotate(-45F),
            text = colorText, color = Color.Black, fontWeight = FontWeight(900)
        )
    }
}

@Composable
fun StartButton(startGameState: Boolean, onStart: () -> Unit) {
    Button(
        enabled = !startGameState,
        onClick = { onStart() }) {
        Text(text = "Let's Go!")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimonDiceTheme {

        }
    }
}