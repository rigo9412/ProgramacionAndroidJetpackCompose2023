package com.lanazirot.examen1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.examen1.presentation.gato.components.GatoViewModel
import com.lanazirot.examen1.ui.theme.Examen1Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GatoBoard()
        }
    }
}

@Composable
fun GatoBoard() {
    Examen1Theme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = Color.Black
        ) {

            val gatoViewModel: GatoViewModel = hiltViewModel()
            val gatoState = gatoViewModel.gato.collectAsState()
            var textoWinner by remember { mutableStateOf("") }

            LaunchedEffect(gatoState.value) {
                val resultado = gatoViewModel.determinarStatusJuego()
                if (resultado != "") {
                    textoWinner = resultado
                    delay(3000)
                    gatoViewModel.resetGame()
                    textoWinner = ""
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(0, 0)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[0][0])
                        }
                    }
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(0, 1)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[0][1])
                        }
                    }
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(0, 2)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[0][2])
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(1, 0)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[1][0])
                        }
                    }
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(1, 1)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[1][1])
                        }
                    }
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(1, 2)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[1][2])
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(2, 0)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[2][0])
                        }
                    }
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(2, 1)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[2][1])
                        }
                    }
                    Column {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Black)
                                .size(100.dp)
                                .background(Color.White)
                                .clickable(onClick = {
                                    gatoViewModel.colocarValorJugadorAGatoArray(2, 2)
                                })
                        ) {
                            Text(text = gatoState.value.gatoArray[2][2])
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Turno de: ${gatoState.value.player}",
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = textoWinner, modifier = Modifier.background(Color.White))
            }
        }
    }
}

