package com.example.simondice19100197

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice19100197.models.Action
import com.example.simondice19100197.models.Game
import com.example.simondice19100197.models.Player
import com.example.simondice19100197.ui.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val blueAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.yoshi)) }
            val redAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.poyo)) }
            val yellowAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.hey)) }
            val greenAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.coin)) }

            var currentActionSimonIndexState by remember { mutableStateOf(game.currentActionSimonIndex) }
            var estadoDeResultado by remember { mutableStateOf<Player?>(null) }
            var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }
            var startPlayState by remember { mutableStateOf(game.endSpeak) }

            var buttonAlert = false
            var bandera = false
            SimonDice19100197Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    LaunchedEffect(currentActionSimonIndexState) {
                        val action = game.getCurrentAction()
                        //println("${action.toString()} = ${game.currentActionSimonIndex}")
                        if (startGameState && !currentActionOn && !game.endSpeak) {
                            when (action) {
                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)
                                else -> { //No suena
                                }
                            }
                            currentActionOn = true
                            delay(1000) //Tiempo entre sonidos
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
                                else -> { //No suena
                                }
                            }
                            currentActionOn = true
                            delay(300) // Tiempo animacion cuadro al pulsar los sonidos
                            currentActionOn = false

                            if(!game.validateAction(currentActionPlayer)){
                                estadoDeResultado = game.end("Partida Anterior:")
                                buttonAlert = true
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
                            buttonAlert = false // <- Se agrego
                        })
                        //Se agrego
                        if (buttonAlert){
                            bandera = alertDialogMessage("Fin del Juego! ❌", estadoDeResultado!!.score.toString(), estadoDeResultado!!.level.toString())
                        }
                        if (bandera){buttonAlert = false}

                        //Mensaje con los resultados, cuando el juego termina
                        if(estadoDeResultado != null) {
                            Text(text = "--------- 🌱 Resultados Anteriores 🌱 ---------\n",color = Color.White,modifier = Modifier.align(
                                CenterHorizontally
                            ))
                            Text(text = estadoDeResultado!!.name, color = Color.White)
                            Text(text = "📈 Puntaje: "+estadoDeResultado!!.score.toString(),color = Color.White)
                            Text(text = "🐉 Nivel: "+estadoDeResultado!!.level.toString(),color = Color.White)
                        }
                    }
                }
            }
        }
    }
}


@Composable //Pop-up - Perdiste!
fun alertDialogMessage(name : String, score : String, level : String) :Boolean {

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
                        Text(text = name,fontSize = 20.sp,fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp).align(alignment = CenterHorizontally))
                    },
                    text = {
                        Text("Nivel alcanzado: " + level + "\nPuntaje final: " + score,fontSize = 15.sp)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Purple200),
                            onClick = {
                                openDialog.value = false
                                x = true
                            }) {
                            Text("Continuar",fontSize = 20.sp, color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Purple200),
                            onClick = {
                                activity?.finish()
                            }) {
                            Text("Salir",fontSize = 20.sp, color = Color.White)
                        }
                    },
                            backgroundColor = Purple700
                )
            }
        }
    }
    return x
}


private fun playAudio(mp: MediaPlayer) {
    println("AUDIO ${mp.isPlaying}")
    if (!mp.isPlaying) {
        mp.start()
    } else {
        mp.pause();
        mp.seekTo(0)
        mp.start()
    }
}


@Composable
fun SimonGame(level : Int,score : Int,endSpeak: Boolean, action: Action,actionPlayer:Action, offCurrent: Boolean, onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = CenterHorizontally

    ) {
        Pad(level,score,if(endSpeak) actionPlayer else action, offCurrent,enablePlay = endSpeak,onClick)

    }
}


@Composable //Pads con los Botones
fun Pad(level : Int, score : Int, action: Action?, onCurrent: Boolean, enablePlay: Boolean,onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(text = "Simon Dice", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 45.sp, modifier = Modifier
            .align(CenterHorizontally)
            .padding(20.dp)
        )

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

        Text(text = "Puntaje: $score   Nivel: $level", fontWeight = FontWeight(700),fontSize = 22.sp, color = Color.White,modifier = Modifier.align(alignment = CenterHorizontally).padding(20.dp))
    }
}


@Composable //Marco de Pads
fun ButtonAction(
    on: Boolean,
    colorOn: Color,
    colorOff: Color,
    colorText: String,
    enablePlay: Boolean,
    action: Action,
    onClick: (Action) -> Unit
){
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            //.rotate(10F)
            .border(width = 1.dp, color = Color.Cyan, shape = RoundedCornerShape(10.dp))
            .width(100.dp)
            .height(100.dp)
            .padding(5.dp)
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
        //Text(text = colorText, fontWeight = FontWeight(900), modifier = Modifier.rotate(-10f))
        Text(text = colorText, fontWeight = FontWeight(900))
    }
}


@Composable //Boton inicio
fun StartButton(startGameState: Boolean, onStart: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        enabled = !startGameState,
        onClick = { onStart() }) {
        Text(text = "Iniciar")
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewstartGameState() {
    Button(
        modifier = Modifier.width(100.dp).height(70.dp),
        enabled = true,
        onClick = { -1 }) {
        Text(text = "Iniciar")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimonDice19100197Theme() {
        val dummyAction = Action.PRESS_RED_BUTTON
        SimonGame(0,0,false, dummyAction,dummyAction, false, onClick = {})
    }
}
