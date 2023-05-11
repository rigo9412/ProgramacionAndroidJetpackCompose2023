package com.example.simondice.domain.game

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.media.MediaPlayer
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simondice.domain.models.*
import com.example.simondice.models.TopViewModel
import kotlinx.coroutines.delay
import com.example.simondice.ui.theme.*
import com.example.simondice.domain.models.getrequesttop.Action
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.DialogDemo
import com.example.simondice.R
import com.example.simondice.domain.models.Player
import com.example.simondice.ui.theme.BlueOff
import com.example.simondice.ui.theme.BlueOn
import com.example.simondice.ui.theme.GreenOff
import com.example.simondice.ui.theme.GreenOn
import com.example.simondice.ui.theme.RedOff
import com.example.simondice.ui.theme.RedOn
import com.example.simondice.ui.theme.YellowOff
import com.example.simondice.ui.theme.YellowOn
import kotlinx.coroutines.delay


fun playAudio(mp: MediaPlayer) {
    if(!mp.isPlaying) {
        mp.start()
    }else{
        mp.pause();
        mp.seekTo(0);
        mp.start()
    }

}

@Composable
fun SimonGame(viewModel: FormViewModel){
    val context = LocalContext.current
    val topViewModel = hiltViewModel<TopViewModel>()
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    val blueAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(context, R.raw.azul)) }
    val redAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(context, R.raw.rojo)) }
    val yellowAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(context, R.raw.amarillo)) }
    val greenAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(context, R.raw.verde)) }

    var currentActionSimonIndexState by remember { mutableStateOf(viewModel.currentActionSimonIndex) }
    var resultsxState by remember { mutableStateOf<Player?>(null) }
    var currentActionPlayer by remember { mutableStateOf(Action.NO_ACTION) }
    var currentActionOn by remember { mutableStateOf(false) }
    var startGameState by remember { mutableStateOf(viewModel.started) }
    var startPlayState by remember { mutableStateOf(viewModel.endSpeak) }

    LaunchedEffect(currentActionSimonIndexState) {
        val action = viewModel.getCurrentAction()
        if (startGameState && !currentActionOn && !viewModel.endSpeak) {
            when (action) {
                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)
                else -> {
                    //NO SUENA
                }
            }
            currentActionOn = true
            delay(1000)
            currentActionOn = false
            viewModel.moveToNextAction()
            currentActionSimonIndexState = viewModel.currentActionSimonIndex

        }
        startPlayState = viewModel.endSpeak
    }

    LaunchedEffect(currentActionPlayer) {
        if (startGameState && viewModel.endSpeak && currentActionPlayer != Action.NO_ACTION && !currentActionOn) {
            when (currentActionPlayer) {
                Action.PRESS_GREEN_BUTTON ->  playAudio(greenAudio)
                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)

                else -> {
                    //NO SUENA
                }
            }
            currentActionOn = true
            delay(300)
            currentActionOn = false

            if(!viewModel.validateAction(currentActionPlayer)){
                resultsxState = viewModel.end("Andrea Martinez")
                topViewModel.postTop(resultsxState!!)
                startGameState = viewModel.started
            }

            currentActionSimonIndexState = viewModel.currentActionSimonIndex
            currentActionPlayer = Action.NO_ACTION

        }
    }

    Column(modifier = Modifier.background(Color.Black),) {
        SimonGameColumn(
            viewModel.level,
            viewModel.score,
            startPlayState,
            viewModel.getCurrentAction(),
            actionPlayer = currentActionPlayer,
            currentActionOn
        ) {
            currentActionPlayer = it
        }

        StartButton(startGameState, onStart = {
            resultsxState = null
            viewModel.start()
            currentActionSimonIndexState = viewModel.currentActionSimonIndex
            startGameState = viewModel.started
        })

        Spacer( modifier = Modifier.size(30.dp))

        Row(
            modifier = Modifier.fillMaxSize().background(Color.Black),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                modifier = Modifier.height(50.dp).width(150.dp),
                onClick = {
                    setShowDialog(true)
                    topViewModel.toCardGetTrue()
                    topViewModel.getTop()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                Text(text = "Mostrar top",color = Color.Black,fontSize = 20.sp)
            }
            // Create alert dialog, pass the showDialog state to this Composable
            DialogDemo(showDialog = showDialog, setShowDialog = setShowDialog)
        }

        if(resultsxState != null)
        {
            alertDialogSample("¡Perdiste!",
                resultsxState!!.score.toString(), resultsxState!!.level.toString())
        }
    }

}

@Composable
fun SimonGameColumn(level : Int, score : Int, endSpeak: Boolean, action: Action, actionPlayer: Action, offCurrent: Boolean, onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Pad(level,score,if(endSpeak) actionPlayer else action, offCurrent,enablePlay = endSpeak,onClick)

    }
}

@Composable
fun Pad(level : Int, score : Int, action: Action?, onCurrent: Boolean, enablePlay: Boolean, onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier.height(400.dp), verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "SCORE: $score   LEVEL: $level", fontWeight = FontWeight(700),fontSize = 27.sp, color = Color.White)
        }
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
            .rotate(45F)
            .border(10.dp, colorOn, RoundedCornerShape(5.dp))
            .size(100.dp)
            .padding(10.dp)
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
        Text(modifier = Modifier
            .padding(4.dp)
            .rotate(-45f),
            text = colorText, fontWeight = FontWeight(700), color = Color.White)
    }
}

@Composable
fun StartButton(startGameState: Boolean, onStart: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.Black),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.height(50.dp).width(100.dp),
            enabled = !startGameState,
            onClick = { onStart() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
            Text(text = "Iniciar",color = Color.Black,fontSize = 20.sp)
        }
    }
}

@Composable
fun alertDialogSample(name : String,score : String,level : String) :Boolean {
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
                        Text(text = name,fontSize = 35.sp,fontWeight = FontWeight(600))
                    },
                    text = {
                        Text("\nNivel alcanzado: "+level+"\nPuntaje final: "+score,fontSize = 25.sp)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                openDialog.value = false
                                x = true
                            }) {
                            Text("Continuar",fontSize = 20.sp, color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                activity?.finish()
                            }) {
                            Text("Salir",fontSize = 20.sp, color = Color.White)
                        }
                    }
                )
            }
        }
    }
    return x
}