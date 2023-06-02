package com.otop.SimonDice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.otop.SimonDice.ui.MainScreen
import com.otop.SimonDice.ui.MainViewModel
import com.otop.SimonDice.ui.TopViewModel
import com.otop.SimonDice.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

//var isPlaying = mutableStateOf(false)
//var sequence = mutableStateOf( mutableListOf<Int> ())
//var currentTurn = mutableStateOf( 0 )
//var level = mutableStateOf(0)
//
//var redPressed = mutableStateOf(false)
//var greenPressed = mutableStateOf(false)
//var bluePressed = mutableStateOf(false)
//var yellowPressed = mutableStateOf(false)
//
//var gameOver = mutableStateOf(false)
//var enaButtons = mutableStateOf(false)
//
//var isSpeaking = mutableStateOf(false)
//
//var roundEnd = mutableStateOf(false )


var i = 0

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel by viewModels()
            val topViewModel : TopViewModel by viewModels()
            LaunchedEffect( viewModel.roundEnd.value ){
                if ( viewModel.roundEnd.value )
                {
                    delay(1000)
                    viewModel.nextRound()
                    viewModel.roundEnd.value = false
                }
            }
            LaunchedEffect(viewModel.gameOver.value){
                if (viewModel.gameOver.value){
                    viewModel.currentTurn.value = 0
                    viewModel.enaButtons.value = false
                    viewModel.redPressed.value = false
                    viewModel.greenPressed.value = false
                    viewModel.bluePressed.value = false
                    viewModel. yellowPressed.value = false
                    viewModel.isPlaying.value = false

                    viewModel.currentTurn.value = 0
                    viewModel.score.value = 0
                    viewModel.sequence.value.clear()
//                    delay(1000)
//                    viewModel.gameOver.value = false
                }
            }
            LaunchedEffect( viewModel.sequence.value ){
                viewModel.isSpeaking.value = true
                viewModel.enaButtons.value = false
                for (i in 0 until viewModel.sequence.value.size){
                    viewModel.colorTrigger(viewModel.sequence.value[i],true)
                    delay(800)
                    viewModel.redPressed.value = false
                    viewModel.greenPressed.value = false
                    viewModel.bluePressed.value = false
                    viewModel.yellowPressed.value = false
                }
                viewModel.isSpeaking.value = false
            }
            LaunchedEffect( viewModel.isSpeaking.value ){
                if (!viewModel.isSpeaking.value && i != 0){
                    viewModel.enaButtons.value = true
                }
                i++
            }
            DimonSiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Scaffold(bottomBar = { bottomBar(viewModel = viewModel) }) {
                        MainScreen(viewModel = viewModel, topVM = topViewModel)
                    }

                }
            }
        }
    }
}

@Composable
fun bottomBar (viewModel: MainViewModel){
    Row() {
        Button(onClick = { viewModel.navigateToGame()}) {
            Text(text = "Juego")
        }
        Button(onClick = { viewModel.navigateToTop()}) {
            Text(text = "Top 10")

        }
    }
}

@Composable
fun SimonButton(
    viewModel: MainViewModel,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = color, disabledContentColor = color, disabledBackgroundColor = color),
        shape = CircleShape,
        enabled = viewModel.enaButtons.value
    ) {}
}