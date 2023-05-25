package com.example.simondice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simondice.domain.models.Player
import com.example.simondice.models.TopViewModel
import com.example.simondice.models.UiState

@Composable
fun DialogDemo(
    showDialog: Boolean, setShowDialog: (Boolean) -> Unit, content: @Composable () -> Unit = {}
) {

    if (showDialog) {
        val topViewModel = hiltViewModel<TopViewModel>()
        when (val state = topViewModel.uiState.collectAsState().value) {
            is UiState.Error -> {
                Text(state.message)
            }

            UiState.Loading -> {
                Text("Loading")
            }

            is UiState.Ready -> {
                Tops(state.top)
            }


        }
    }
}

@Composable
fun Tops(players: List<Player>) {

    val topViewModel = hiltViewModel<TopViewModel>()
    if (topViewModel.toCardGetState.collectAsState().value) {

        Dialog(onDismissRequest = {
            topViewModel.toCardGetFalse()
        }) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                shape = RoundedCornerShape(10.dp),
                elevation = 10.dp
            ) {


                Column(
                    content = {

                        Row() {
                            Column {
                                Text(text = "Nombre", modifier = Modifier.padding(10.dp))
                            }
                            Column {
                                Text(text = "Puntaje", modifier = Modifier.padding(10.dp))
                            }
                        }
                        for (player in players) {
                            Row() {
                                Column {
                                    Text(text = player.name, modifier = Modifier.padding(10.dp))
                                }
                                Column {
                                    Text(text = player.score.toString(), modifier = Modifier.padding(10.dp))
                                }
                            }
                        }
                        Button(onClick = {

                            topViewModel.toCardGetFalse()
                            return@Button

                        }) {
                            Text(text = "Cerrar")
                        }
                    }, modifier = Modifier

                        .verticalScroll(rememberScrollState())
                )

            }
        }
    }
}






