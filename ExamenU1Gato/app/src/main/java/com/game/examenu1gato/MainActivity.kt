package com.game.examenu1gato

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.examenu1gato.ui.theme.ExamenU1GatoTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selected1 by remember { mutableStateOf(false) }
            var selected2 by remember { mutableStateOf(false) }
            var selected3 by remember { mutableStateOf(false) }
            var selected4 by remember { mutableStateOf(false) }
            var selected5 by remember { mutableStateOf(false) }
            var selected6 by remember { mutableStateOf(false) }
            var selected7 by remember { mutableStateOf(false) }
            var selected8 by remember { mutableStateOf(false) }
            var selected9 by remember { mutableStateOf(false) }
            var startOn by remember { mutableStateOf(false) }
            var texto1 by remember { mutableStateOf("") }
            var texto2 by remember { mutableStateOf("") }
            var texto3 by remember { mutableStateOf("") }
            var texto4 by remember { mutableStateOf("") }
            var texto5 by remember { mutableStateOf("") }
            var texto6 by remember { mutableStateOf("") }
            var texto7 by remember { mutableStateOf("") }
            var texto8 by remember { mutableStateOf("") }
            var texto9 by remember { mutableStateOf("") }
            var textoGanador by remember { mutableStateOf("") }

            var gameFinished by remember { mutableStateOf(false) }
            var turnos by remember { mutableStateOf(9) }

            var combinacionP1 by remember { mutableStateOf("") }
            var combinacionP2 by remember { mutableStateOf("") }

            var color by remember { mutableStateOf(Color) }
            var p1 by remember { mutableStateOf(false) }
            var p2 by remember { mutableStateOf(false) }

            ExamenU1GatoTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(key1 = startOn)
                    {
                        if(startOn)
                        {
                            p1 = true
                        }
                    }


                    /*LaunchedEffect(key1 = gameFinished)
                    {
                        if(gameFinished)
                        {
                            p1 = false
                            p2 = false
                            startOn = !startOn
                            combinacionP1 = ""; combinacionP2 = ""
                        }
                    }*/
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
                            Text("GATO", fontFamily = FontFamily.SansSerif,
                                fontSize = 50.sp)
                        }
                        Row(modifier= Modifier
                            .fillMaxWidth()
                            .padding(19.dp, 15.dp),
                            horizontalArrangement = Arrangement.Center){
                            Text("$textoGanador", fontFamily = FontFamily.SansSerif,
                                fontSize = 50.sp)
                        }
                        Row (modifier = Modifier
                            //.padding(2.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    selected1 = !selected1
                                    if(p1)
                                    {
                                        combinacionP1 += "1"
                                        texto1 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "1"
                                        texto1 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected1,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto1")
                            }
                            Button(
                                onClick = {
                                    selected2 = !selected2
                                    if(p1)
                                    {
                                        combinacionP1 += "2"
                                        texto2 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "2"
                                        texto2 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected2,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto2")
                            }
                            Button(
                                onClick = {
                                    selected3 = !selected3
                                    if(p1)
                                    {
                                        combinacionP1 += "3"
                                        texto3 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "3"
                                        texto3 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected3,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto3")
                            }
                        }

                        Row (modifier = Modifier
                            //.padding(2.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    selected4 = !selected4
                                    if(p1)
                                    {
                                        combinacionP1 += "4"
                                        texto4 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "4"
                                        texto4 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected4,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto4")
                            }
                            Button(
                                onClick = {
                                    selected5 = !selected5
                                    if(p1)
                                    {
                                        combinacionP1 += "5"
                                        texto5 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "5"
                                        texto5 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected5,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto5")
                            }
                            Button(
                                onClick = {
                                    selected6 = !selected6
                                    if(p1)
                                    {
                                        combinacionP1 += "6"
                                        texto6 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "6"
                                        texto6 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected6,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto6")
                            }
                        }
                        Row (modifier = Modifier
                            //.padding(2.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    selected7 = !selected7
                                    if(p1)
                                    {
                                        combinacionP1 += "7"
                                        texto7 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "7"
                                        texto7 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected7,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto7")
                            }
                            Button(
                                onClick = {
                                    selected8 = !selected8
                                    if(p1)
                                    {
                                        combinacionP1 += "8"
                                        texto8 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "8"
                                        texto8 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected8,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto8")
                            }
                            Button(
                                onClick = {
                                    selected9 = !selected9
                                    if(p1)
                                    {
                                        combinacionP1 += "9"
                                        texto9 = "X"
                                        p1 = false
                                    }
                                    else{
                                        combinacionP2 += "9"
                                        texto9 = "O"
                                        p1 = true
                                        //p2 = false
                                    }
                                    textoGanador = Comprobar(combinacionP1,combinacionP2)
                                    if(textoGanador != ""){
                                        startOn = !startOn
                                        combinacionP1 = ""; combinacionP2=""
                                        selected1 = false
                                        selected2 = false
                                        selected3 = false
                                        selected4 = false
                                        selected5 = false
                                        selected6 = false
                                        selected7 = false
                                        selected8 = false
                                        selected9 = false

                                        texto1 = ""; texto2=""; texto3=""
                                        texto4 = ""; texto5=""; texto6=""
                                        texto7 = ""; texto8=""; texto9=""
                                    }
                                },
                                enabled = !selected9,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(120.dp)
                                    .height(110.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("$texto9")
                            }
                        }
                        Row (modifier = Modifier
                            //.padding(2.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                          startOn = !startOn
                                },
                                enabled = !startOn,
                                modifier = Modifier
                                    .padding(15.dp)
                                    .width(160.dp)
                                    .height(60.dp)
                            ) {
                                Text("Iniciar")
                            }
                        }
                    }
                }
            }
        }
    }
}


fun Comprobar(cad1: String, cad2: String):String{
    if(cad1.contains("123") || cad1.contains("456") || cad1.contains("789")
        || cad1.contains("147") || cad1.contains("258") || cad1.contains("369")
        || cad1.contains("159") || cad1.contains("357"))
    {
        return "Ganó Jugador 1"
    }
    if(cad2.contains("123") || cad2.contains("456") || cad2.contains("789")
        || cad2.contains("147") || cad2.contains("258") || cad2.contains("369")
        || cad2.contains("159") || cad2.contains("357"))
    {
        return "Ganó Jugador 2"
    }
    return ""
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExamenU1GatoTheme {
        Greeting("Android")
    }
}