package com.example.simondice

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.models.ButtonColor
import com.example.simondice.models.Game
import com.example.simondice.ui.theme.*
import kotlinx.coroutines.delay
import kotlin.math.round

var isPlaying = mutableStateOf(false)
var sequence = mutableStateOf( mutableListOf<Int> ())
var currentTurn = mutableStateOf( 0 )
var level = mutableStateOf(0)

var redPressed = mutableStateOf(false)
var greenPressed = mutableStateOf(false)
var bluePressed = mutableStateOf(false)
var yellowPressed = mutableStateOf(false)

var gameOver = mutableStateOf(false)
var enaButtons = mutableStateOf(false)

var isSpeaking = mutableStateOf(false)


var i = 0
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(gameOver.value){
                if (gameOver.value){
                    currentTurn.value = 0
                    enaButtons.value = false
                    redPressed.value = false
                    greenPressed.value = false
                    bluePressed.value = false
                    yellowPressed.value = false
                    isPlaying.value = false

                    level.value = 0
                    currentTurn.value = 0
                    Game().score = 0
                    sequence.value.clear()
                    delay(1000)
                    gameOver.value = false
                }
            }
            LaunchedEffect( sequence.value ){
                isSpeaking.value = true
                for (i in 0 until sequence.value.size){
                    Game().colorTrigger(applicationContext, sequence.value[i],true)
                    delay(800)
                    redPressed.value = false
                    greenPressed.value = false
                    bluePressed.value = false
                    yellowPressed.value = false
                }
                isSpeaking.value = false
            }
            LaunchedEffect( isSpeaking.value ){
                if (!isSpeaking.value && i != 0){
                   enaButtons.value = true
                }
                i++
            }
            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    //LoopingSound(applicationContext, R.raw.radio)
                    SimonGame(
                        redClicked = { Game().colorTrigger(applicationContext,0,false)},
                        greenClicked = {Game().colorTrigger(applicationContext,1,false)},
                        blueClicked = {Game().colorTrigger(applicationContext,2,false)},
                        yellowClicked = {Game().colorTrigger(applicationContext,3,false)}
                    )

                }
            }
        }
    }
}

@Composable
fun LoopingSound(context: Context, @RawRes soundRes: Int) {
    val mediaPlayer = remember { MediaPlayer.create(context, soundRes) }
    DisposableEffect(isPlaying) {
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        mediaPlayer.setVolume(2f,2f)
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {

    }
}

@Composable
fun SimonGame(
    redClicked: () -> Unit,
    greenClicked: () -> Unit,
    blueClicked: () -> Unit,
    yellowClicked: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row() {
            if (gameOver.value) Text(text = "Perdiste", color = Color.Red, fontSize = 50.sp)
        }

        Row(horizontalArrangement = Arrangement.Center) {
            SimonButton(color = redSimon, onClick = redClicked,
                Modifier
                    .background(if (redPressed.value) redLightSimon else redSimon, CircleShape)
                    .size(100.dp)
                    .padding(8.dp),
            )
            SimonButton(color = greenSimon, onClick = greenClicked,
                Modifier
                    .background(
                        if (greenPressed.value) greenLightSimon else greenSimon,
                        CircleShape
                    )
                    .size(100.dp)
                    .padding(8.dp))
        }
        Row(horizontalArrangement = Arrangement.Center) {
            SimonButton(color = blueSimon, onClick = blueClicked,
                Modifier
                    .background(if (bluePressed.value) blueLightSimon else blueSimon, CircleShape)
                    .size(100.dp)
                    .padding(8.dp))
            SimonButton(color = yellowSimon, onClick = yellowClicked,
                Modifier
                    .background(
                        if (yellowPressed.value) yellowLightSimon else yellowSimon,
                        CircleShape
                    )
                    .size(100.dp)
                    .padding(8.dp))
        }
        Button(onClick = { Game().StartGame(); isPlaying.value = true }, enabled = !isPlaying.value) {
            Text(text = "Comenzar")
        }
//        Text(text = "Secuencia: "+sequence.value.toString(), color = Color.White)
  //      Text(text = "Turno: "+ currentTurn.value.toString(), color = Color.White)
    //    Text(text = "Ronda: "+ level.value.toString(), color = Color.White)
    }
}

@Composable
fun SimonButton(
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = color, disabledContentColor = color, disabledBackgroundColor = color),
        shape = CircleShape,
        enabled = enaButtons.value
    ) {}
}