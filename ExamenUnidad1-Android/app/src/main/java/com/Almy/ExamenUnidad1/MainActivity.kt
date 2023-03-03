package com.Almy.ExamenUnidad1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Almy.ExamenUnidad1.ui.theme.ExamenUnidad1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var jugadorXTurno by remember { mutableStateOf(false) }
            var jugadorOTurno by remember { mutableStateOf(false) }
            var Seleccion by remember { mutableStateOf(false) }
            var botonElegido by remember { mutableStateOf("") }
            var turnoJugador by remember { mutableStateOf("") }
            var botonTexto by remember { mutableStateOf("") }
            ExamenUnidad1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                   LaunchedEffect(key1 = turnoJugador){
                       botonTexto = "X"
                       turnoJugador == "JugadorO"
                    }


                    LaunchedEffect(key1 = turnoJugador){
                        botonTexto = "O"
                        turnoJugador == "JugadorX"
                    }



                    //EL DISEÑOOOOOOO
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(19.dp, 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween

                        )
                        {
                            Text("JUEGO GATO INICIADO \n", fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(19.dp, 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Text(
                                "Le toca jugar a: $turnoJugador \n",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 20.sp
                            )
                        }

                        //BOTONES
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            //BOTON 1
                            Button(
                                onClick = {
                                    if(turnoJugador == "JugadorX"){

                                    }
                                    else{

                                    }
                                },
                                //interactionSource = interactionSource,
                                enabled =  true,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green
                                )
                            )
                            {
                                Text(botonTexto)
                            }

                            Button(
                                onClick = {
                                    if(turnoJugador == "JugadorX"){
                                        botonTexto = "X"
                                        turnoJugador == "JugadorO"
                                    }
                                    else{
                                        botonTexto = "O"
                                        turnoJugador == "Jugadorx"
                                    }
                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green))
                            {
                                Text(botonTexto)
                            }

                            Button(
                                onClick = {
                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green))
                            {
                                Text(botonTexto)
                            }
                        }


                        //BOTONES
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween

                        )
                        {
                            //BOTON 1
                            Button(
                                onClick = {
                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green
                                )
                            )
                            {
                                Text(botonTexto)
                            }

                            Button(
                                onClick = {

                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green))
                            {
                                Text(botonTexto)
                            }

                            Button(
                                onClick = {
                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green))
                            {
                                Text(botonTexto)
                            }
                        }



                        //BOTONES
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween

                        )
                        {
                            //BOTON 1
                            Button(
                                onClick = {
                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green
                                )
                            )
                            {
                                Text(botonTexto)
                            }

                            Button(
                                onClick = {

                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green))
                            {
                                Text(botonTexto)
                            }

                            Button(
                                onClick = {
                                },
                                //interactionSource = interactionSource,
                                //enabled = !selected1,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green))
                            {
                                Text("")
                            }

                        }
                        //BOTONES
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center

                        )
                        {
                            //BOTON INICIAR
                            Button(
                                onClick = {
                                    turnoJugador = "JugadorX"
                                    jugadorXTurno =  true
                                },
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green))
                            {
                                Text("Iniciar")
                            }

                        }

                    }
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExamenUnidad1Theme {

    }
}