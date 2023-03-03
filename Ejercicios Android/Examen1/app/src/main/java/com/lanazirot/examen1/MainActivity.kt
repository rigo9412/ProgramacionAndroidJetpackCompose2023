package com.lanazirot.examen1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lanazirot.examen1.presentation.gato.components.GatoViewModel
import com.lanazirot.examen1.ui.theme.Examen1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GatoBoard()
                }
            }
        }
    }
}

//Create a 3x3 matrix composable

@Composable
fun GatoBoard(gatoViewModel: GatoViewModel = GatoViewModel()) {
    val gatoState = gatoViewModel.gato.collectAsState()
    var textoWinner by remember {  mutableStateOf("") }
    //LaunchedEffect para escuchar cambios en el board




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
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White)
                    .clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(0, 0)
                    })
                ){
                    Text(text = gatoState.value.gatoArray[0][0])
                }
            }
            Column() {
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White)
                    .clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(0, 1)
                    })
                ){
                    Text(text = gatoState.value.gatoArray[0][1])
                }
            }
            Column() {
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White)
                    .clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(0, 2)
                    })
                ){
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
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White)
                    .clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(1, 0)
                    })
                ){
                    Text(text = gatoState.value.gatoArray[1][0])
                }
            }
            Column() {
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White).clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(1, 1)
                    })
                ){
                    Text(text = gatoState.value.gatoArray[1][1])
                }
            }
            Column() {
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White).clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(1, 2)
                    })
                ){
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
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White).clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(2, 0)
                    })
                ){
                    Text(text = gatoState.value.gatoArray[2][0])
                }
            }
            Column() {
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White).clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(2, 1)
                    })
                ){
                    Text(text = gatoState.value.gatoArray[2][1])
                }
            }
            Column() {
                Box(modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(Color.White).clickable(onClick = {
                        gatoViewModel.colocarValorJugadorAGatoArray(2, 2)
                    })
                ){
                    Text(text = gatoState.value.gatoArray[2][2])
                }
            }
        }

        //Spacer
        Spacer(modifier = Modifier.height(50.dp))
        //Texto de a quien le toca jugar si a X o a O
        Text(text = "Turno de: ${gatoState.value.player}", modifier = Modifier.background(Color.White))

        Spacer(modifier = Modifier.height(50.dp))

        //Winner text
        Text(text = textoWinner , modifier = Modifier.background(Color.White))

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Examen1Theme {
        GatoBoard()
    }
}
