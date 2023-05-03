package com.ezequiel.simondice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ezequiel.simondice.domain.modelo.Player
import com.ezequiel.simondice.modelo.TopViewModel
import com.ezequiel.simondice.models.UiState

@Composable
fun SplashScreen() {
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Fin del juego!",
            fontWeight = FontWeight(900),
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            style = TextStyle(
                fontSize = 50.sp,
                shadow = Shadow(
                    color = Color.DarkGray,
                    blurRadius = 3f
                )
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}

@Composable
fun ComposeDialogDemo() {

    // State to manage if the alert dialog is showing or not.
    // Default is false (not showing)
    val topViewModel = hiltViewModel<TopViewModel>()
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    Column(
        // Make the column fill the whole screen space (width and height).
        modifier = Modifier.fillMaxSize(),
        // Place all children at center horizontally.
        horizontalAlignment = Alignment.CenterHorizontally,
        // Place all children at center vertically.
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {

                setShowDialog(true)
                topViewModel.toCardGetTrue()
                topViewModel.getTop()

            }) {
            Text("Mejores puntajes")
        }
        // Create alert dialog, pass the showDialog state to this Composable
        DialogDemo(showDialog = showDialog, setShowDialog = setShowDialog)
    }
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
                    .height(500.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 10.dp
            ) {


                Column(
                    content = {

                        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(text = " Nombre",
                                    fontWeight = FontWeight(900),
                                    color = Color.Black,
                                    fontFamily = FontFamily.Cursive,
                                    style = TextStyle(
                                        fontSize = 25.sp,
                                        shadow = Shadow(
                                            color = Color.DarkGray,
                                            blurRadius = 3f
                                        )
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(140.dp))
                            Column {
                                Text(text = "Puntaje   ",
                                    fontWeight = FontWeight(900),
                                    color = Color.Black,
                                    fontFamily = FontFamily.Cursive,
                                    style = TextStyle(
                                        fontSize = 25.sp,
                                        shadow = Shadow(
                                            color = Color.DarkGray,
                                            blurRadius = 3f
                                        )
                                    )
                                )
                            }
                        }
                        for (player in players) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Column () {
                                    Text(text = player.name, modifier = Modifier.padding(10.dp))
                                }
                                Column (){
                                    Text(text = player.score.toString(), modifier = Modifier.padding(10.dp))
                                }
                            }
                        }
                        Button(onClick = {

                            topViewModel.toCardGetFalse()
                            return@Button

                        }) {
                            Text(text = "                        Cerrar                          ")
                        }
                    }, modifier = Modifier

                        .verticalScroll(rememberScrollState())
                )

            }
        }
    }
}






