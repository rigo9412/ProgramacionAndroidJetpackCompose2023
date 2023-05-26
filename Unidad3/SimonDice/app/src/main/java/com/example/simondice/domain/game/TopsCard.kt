package com.example.simondice.domain.game

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simondice.domain.models.Player
import com.example.simondice.models.TopViewModel
import com.example.simondice.models.UiState

@Composable
fun ComposeDialogDemo() {
    val topViewModel = hiltViewModel<TopViewModel>()
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    Button(
        onClick = {
            setShowDialog(true)
            topViewModel.toCardGetTrue()
            topViewModel.getTop()

        }) {
        Text("Mostrar campeones")
    }
    // Create alert dialog, pass the showDialog state to this Composable
    DialogDemo(showDialog = showDialog, setShowDialog = setShowDialog)
}

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
                elevation = 50.dp
            ) {
                Column(
                    content = {
                        Text(
                            text = "Top Puntajes 🎖",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Nombre",
                                modifier = Modifier.weight(1f).padding(10.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Puntaje",
                                modifier = Modifier.weight(1f).padding(10.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        for (player in players) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = player.name,
                                    modifier = Modifier.weight(1f).padding(10.dp)
                                )
                                Text(
                                    text = player.score.toString(),
                                    modifier = Modifier.weight(1f).padding(10.dp),
                                    textAlign = TextAlign.End
                                )
                            }
                        }
                        Button(
                            onClick = {
                                topViewModel.toCardGetFalse()
                                return@Button
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                        ) {
                            Text(text = "Cerrar", color = Color.White)
                        }
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        }
    }
}



