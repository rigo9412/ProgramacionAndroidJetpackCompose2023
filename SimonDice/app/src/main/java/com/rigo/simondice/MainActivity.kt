package com.rigo.simondice

import android.app.GameState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rigo.simondice.domain.models.Actions
import com.rigo.simondice.domain.models.Game
import com.rigo.simondice.ui.theme.*
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import java.time.Duration

class MainActivity : ComponentActivity() {
    val game = Game()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentActionIndexState by remember { mutableStateOf(-1) }
            var currentActionOn by remember { mutableStateOf(false) }
            var startGameState by remember { mutableStateOf(game.started) }


            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(currentActionIndexState) {
                        if(startGameState) {
                            delay(200)
                            currentActionOn = false
                            delay(200)
                            game.moveToNextAction()
                            currentActionIndexState = game.currentActionIndex
                            currentActionOn = true

                            if(currentActionIndexState == game.lastActionIndex){
                                game.end("dummy")
                                startGameState = false
                                currentActionIndexState = -1

                            }
                        }
                    }

                    Column() {
                        SimonGame(game.endSpeak, game.getCurrentAction(),currentActionOn)
                        StartButton(startGameState,onStart = {
                            game.start()
                            currentActionIndexState = game.currentActionIndex
                            startGameState = game.started

                        })
                    }
                }
            }
        }
    }
}





@Composable
fun SimonGame(simonSpeak: Boolean,action: Actions?,offCurrent: Boolean){

    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Pad(action,offCurrent)
    }
}

@Composable
fun Pad(action: Actions?,offCurrent: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(on = action == Actions.PRESS_GREEN_BUTTON && !offCurrent , colorOn = GreenOn, colorOff = GreenOff, colorText = "Verde")
            ButtonAction(on = action == Actions.PRESS_RED_BUTTON && !offCurrent, colorOn = RedOn, colorOff = RedOff, colorText = "Rojo")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(on = action == Actions.PRESS_BLUE_BUTTON && !offCurrent, colorOn = BlueOn, colorOff = BlueOff, colorText = "Azul")
            ButtonAction(on = action == Actions.PRESS_YELLOW_BUTTON && !offCurrent, colorOn = YellowOn, colorOff = YellowOff, colorText = "Amarillo")
        }
    }
}

@Composable
fun ButtonAction(on: Boolean,colorOn: Color, colorOff: Color,colorText: String){
    Box( modifier = Modifier
        .width(100.dp)
        .height(100.dp)
        .padding(4.dp)
        .background(if (on) colorOn else colorOff),
        contentAlignment = Alignment.Center
    ) {
        Text(text = colorText, fontWeight = FontWeight(900))
    }
}

@Composable
fun StartButton(startGameState: Boolean, onStart: () -> Unit){
    Button(
        enabled = !startGameState,
        onClick = { onStart() }) {
        Text(text = "Iniciar")
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun DefaultPreview() {
    SimonDiceTheme {
        val dummyAction = Actions.PRESS_RED_BUTTON
        SimonGame(false,dummyAction,false)
        
    }
}