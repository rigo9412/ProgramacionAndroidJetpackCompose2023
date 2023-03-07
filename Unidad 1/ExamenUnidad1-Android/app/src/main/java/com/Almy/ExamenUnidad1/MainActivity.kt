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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Almy.ExamenUnidad1.ui.theme.ExamenUnidad1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var opcion1 by remember { mutableStateOf("") }
            var opcion2 by remember { mutableStateOf("") }
            var opcion3 by remember { mutableStateOf("") }
            var opcion4 by remember { mutableStateOf("") }
            var opcion5 by remember { mutableStateOf("") }
            var opcion6 by remember { mutableStateOf("") }
            var opcion7 by remember { mutableStateOf("") }
            var opcion8 by remember { mutableStateOf("") }
            var opcion9 by remember { mutableStateOf("") }

            var eleccion1 by remember { mutableStateOf(false) }
            var eleccion2 by remember { mutableStateOf(false) }
            var eleccion3 by remember { mutableStateOf(false) }
            var eleccion4 by remember { mutableStateOf(false) }
            var eleccion5 by remember { mutableStateOf(false) }
            var eleccion6 by remember { mutableStateOf(false) }
            var eleccion7 by remember { mutableStateOf(false) }
            var eleccion8 by remember { mutableStateOf(false) }
            var eleccion9 by remember { mutableStateOf(false) }
            var empezar by remember { mutableStateOf(false) }

            var opcionGanador by remember { mutableStateOf("") }
            var turnos by remember { mutableStateOf(9) }

            var combinacionJugador1 by remember { mutableStateOf("") }
            var combinacionJugador2 by remember { mutableStateOf("") }

            var Jugador1 by remember { mutableStateOf(false) }
            var Jugador2 by remember { mutableStateOf(false) }

            ExamenUnidad1Theme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(key1 = empezar)
                    {
                        if(empezar)
                        {
                            val eleccion = mutableListOf(false, false, false, false, false, false, false, false, false)
                            val opcion = mutableListOf("", "", "", "", "", "", "", "", "")

                            for (i in 0..8) {
                                eleccion[i] = false
                                opcion[i] = ""
                            }
                            turnos = 9
                            Jugador1 = true
                        }
                    }

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row(modifier= Modifier
                            .fillMaxWidth()
                            .padding(19.dp, 15.dp),
                            horizontalArrangement = Arrangement.Center){
                            Text("Juego del gato", fontFamily = FontFamily.Default,
                                fontSize = 35.sp)
                        }
                        Row(modifier= Modifier
                            .fillMaxWidth()
                            .padding(19.dp, 15.dp),
                            horizontalArrangement = Arrangement.Start){
                            Text("El ganador es: " + "$opcionGanador", fontFamily = FontFamily.Default,
                                fontSize = 25.sp)
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    eleccion1 = !eleccion1
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "1"
                                        opcion1 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "1"
                                        opcion1 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion1,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion1", fontSize = 30.sp)
                            }
                            Button(
                                onClick = {
                                    eleccion2 = !eleccion2
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "2"
                                        opcion2 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "2"
                                        opcion2 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion2,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion2", fontSize = 30.sp)
                            }
                            Button(
                                onClick = {
                                    eleccion3 = !eleccion3
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "3"
                                        opcion3 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "3"
                                        opcion3 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion3,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion3", fontSize = 30.sp)
                            }
                        }

                        Row (modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    eleccion4 = !eleccion4
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "4"
                                        opcion4 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "4"
                                        opcion4 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion4,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion4", fontSize = 30.sp)
                            }
                            Button(
                                onClick = {
                                    eleccion5 = !eleccion5
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "5"
                                        opcion5 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "5"
                                        opcion5 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion5,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion5", fontSize = 30.sp)
                            }
                            Button(
                                onClick = {
                                    eleccion6 = !eleccion6
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "6"
                                        opcion6 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "6"
                                        opcion6 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion6,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion6", fontSize = 30.sp)
                            }
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    eleccion7 = !eleccion7
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "7"
                                        opcion7 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "7"
                                        opcion7 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion7,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion7", fontSize = 30.sp)
                            }
                            Button(
                                onClick = {
                                    eleccion8 = !eleccion8
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "8"
                                        opcion8 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "8"
                                        opcion8 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion8,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion8", fontSize = 30.sp)
                            }
                            Button(
                                onClick = {
                                    eleccion9 = !eleccion9
                                    turnos--
                                    if(Jugador1)
                                    {
                                        combinacionJugador1 += "9"
                                        opcion9 = "X"
                                        Jugador1 = false
                                    }
                                    else{
                                        combinacionJugador2 += "9"
                                        opcion9 = "O"
                                        Jugador1 = true
                                    }
                                    opcionGanador = Comprobar2(combinacionJugador1,combinacionJugador2)
                                    if(opcionGanador != ""){
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                    else if (turnos == 0)
                                    {
                                        opcionGanador = "Empate"
                                        empezar = !empezar
                                        combinacionJugador1 = ""; combinacionJugador2=""
                                    }
                                },
                                enabled = !eleccion9,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(100.dp)
                                    .height(100.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, disabledBackgroundColor = Color.Gray)
                            ) {
                                Text("$opcion9", fontSize = 30.sp)
                            }
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    empezar = !empezar
                                },
                                enabled = !empezar,
                                modifier = Modifier
                                    .padding(30.dp)
                                    .width(200.dp)
                                    .height(40.dp)
                            ) {
                                Text("Comenzar juego")
                            }
                        }
                    }
                }
            }
        }
    }
}


fun Comprobar(cad1: String, cad2: String):String{
    val ganadoras = listOf("123", "456", "789", "147", "258", "369", "159", "357")
    for (combo in ganadoras) {
        if (cad1.contains(combo)) {
            return "jugador 1"
        } else if (cad2.contains(combo)) {
            return "jugador 2"
        }
    }
    return ""
}

fun Comprobar2(cad1: String, cad2: String):String{
    val winningCombinations = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(3, 6, 9),
        intArrayOf(1, 5, 9),
        intArrayOf(3, 5, 7)
    )

    for (combination in winningCombinations) {
        var jugador1Gana = true
        var jugador2Gana = true
        for (move in combination) {
            if (!cad1.contains(move.toString())) {
                jugador1Gana = false
            }
            if (!cad2.contains(move.toString())) {
                jugador2Gana = false
            }
        }
        if (jugador1Gana) {
            return "jugador1"
        } else if (jugador2Gana) {
            return "jugador2"
        }
    }
    return ""
}
