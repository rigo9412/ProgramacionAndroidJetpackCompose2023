package com.otop.SimonDice.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.otop.SimonDice.SimonButton
import com.otop.SimonDice.ui.theme.blueLightSimon
import com.otop.SimonDice.ui.theme.blueSimon
import com.otop.SimonDice.ui.theme.greenLightSimon
import com.otop.SimonDice.ui.theme.greenSimon
import com.otop.SimonDice.ui.theme.redLightSimon
import com.otop.SimonDice.ui.theme.redSimon
import com.otop.SimonDice.ui.theme.yellowLightSimon
import com.otop.SimonDice.ui.theme.yellowSimon

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val currentScreen: Screen by viewModel.currentScreen.collectAsState()

    when (currentScreen) {
        is Screen.Game -> SimonGame(viewModel)
        is Screen.Top -> TopScreen(viewModel)
        else -> {}
    }
}

@Composable
fun SimonGame(viewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = viewModel.level.value.toString(), fontSize = 40.sp)
        Column() {
            if (viewModel.gameOver.value) {
                Text(text = "Perdiste", color = Color.Red, fontSize = 50.sp)
                TextField(value = viewModel.topName.value, onValueChange = {viewModel.topName.value = it}
                    , label = { Text(text = "Ingresa tu nombre")}, modifier = Modifier.testTag("txtNombre"))
                Button(onClick = {viewModel.updateTop()}) {
                    Text(text = "Aceptar", modifier = Modifier.testTag("btnUpdate"))
                }
            }
        }

        Row(horizontalArrangement = Arrangement.Center) {
            SimonButton(color = redSimon, onClick = { viewModel.colorTrigger(0,false) }, viewModel = viewModel,
                modifier = Modifier
                    .background(
                        if (viewModel.redPressed.value) redLightSimon else redSimon,
                        CircleShape
                    )
                    .size(100.dp)
                    .padding(8.dp),
            )
            SimonButton(color = greenSimon, onClick = { viewModel.colorTrigger(1,false) }, viewModel = viewModel,
                modifier = Modifier
                    .background(
                        if (viewModel.greenPressed.value) greenLightSimon else greenSimon,
                        CircleShape
                    )
                    .size(100.dp)
                    .padding(8.dp)
            )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            SimonButton(color = blueSimon, onClick = { viewModel.colorTrigger(2,false) }, viewModel = viewModel,
                modifier = Modifier
                    .background(
                        if (viewModel.bluePressed.value) blueLightSimon else blueSimon,
                        CircleShape
                    )
                    .size(100.dp)
                    .padding(8.dp)
            )
            SimonButton(color = yellowSimon, onClick = { viewModel.colorTrigger(3,false) }, viewModel = viewModel,
                modifier = Modifier
                    .background(
                        if (viewModel.yellowPressed.value) yellowLightSimon else yellowSimon,
                        CircleShape
                    )
                    .size(100.dp)
                    .padding(8.dp)
            )
        }
        Button(onClick = { viewModel.StartGame(); viewModel.isPlaying.value = true }, enabled = viewModel.btnBool.value) {
            Text(text = "Comenzar")
        }
    }
}

@Composable
fun TopScreen(viewModel: MainViewModel): Unit {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top 10", fontSize = 40.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(items = viewModel.toptenNombre.value){player ->
                    Text(text = player)
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(items = viewModel.toptenScore.value){score ->
                    Text(text = score.toString())
                }
            }
        }
    }

}