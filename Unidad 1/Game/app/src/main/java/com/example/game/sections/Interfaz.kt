package com.example.game.sections

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.ListFormatter.Width
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.game.ui.theme.*
import com.example.game.R.raw
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

class Interfaz : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

/*@Composable
fun Cuadros(onClick: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Box(modifier = Modifier
                .clickable { onClick() }
                .background(DarkGreen)
                .width(150.dp)
                .height(140.dp)
            )
            Box(modifier = Modifier
                .background(Color.White)
                .width(10.dp)
                .height(10.dp))
            Box(modifier = Modifier
                .background(DarkRed)
                .width(150.dp)
                .height(140.dp))
        }
        Box(modifier = Modifier
            .background(Color.White)
            .width(10.dp)
            .height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Box(modifier = Modifier
                .background(DarkBlue)
                .width(150.dp)
                .height(140.dp))
            Box(modifier = Modifier
                .background(Color.White)
                .width(10.dp)
                .height(10.dp))
            Box(modifier = Modifier
                .background(DarkYellow)
                .width(150.dp)
                .height(140.dp))
        }
    }
}*/

//variables globales xd
//var greenPressed = false//: State<Boolean> = mutableStateOf(false)
//var redPressed = false//: State<Boolean> = mutableStateOf(false)
//var bluePressed = false //: State<Boolean> = mutableStateOf(false)
//var yellowPressed = false//:State<Boolean> = mutableStateOf(false)

//var combinacion : String = ""
//var combinacionCorrecta : String = ""
//var combinacionPrevia : String = ""

//val combinacionesNvl1 = listOf("0", "1", "2", "3")
//val combinacionesNvl2 = listOf("01", "13", "23", "30")
//val combinacionesNvl3 = listOf("013", "132", "230", "023")
//val combinacionesNvl4 = listOf("0112", "1013", "1203", "3122")

//var nvl : Int = 1
//var conteo : Int = 1

//var blnStart = false
//var blnContinuar = true
//var blnReproducir = false

/*@SuppressLint("SuspiciousIndentation")
@Composable
fun BotonVerde(context: Context?){
    var selected by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    var greenOn by remember { mutableStateOf(greenPressed) }

    LaunchedEffect(key1 = greenOn){
        if(greenOn)
        {
            selected = !selected
            greenOn = !greenOn
        }
    }

    LaunchedEffect(key1 = selected)
    {
        if(selected) {
            MediaPlayer
                .create(context, raw.sound1)
                .start()
            delay(1000)
            selected = !selected
        }
    }

    Button(
        onClick = { selected = !selected
            if(!greenOn) combinacion += "0"
            conteo-- },
        interactionSource = interactionSource,
        enabled = !selected,
        modifier = Modifier
            .width(180.dp)
            .height(170.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkGreen,
            disabledBackgroundColor = Color.Green)
    ) {
        Text("Verde")
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BotonRojo(context: Context?){
    var selected by remember { mutableStateOf(false) }
    //var redPressed1 by remember { mutableStateOf(redPressed) }
    LaunchedEffect(key1 = redPressed){
        if(redPressed)
        {
            selected = !selected
            redPressed = false
        }
    }

    LaunchedEffect(key1 = selected)
    {
        if(selected) {
            MediaPlayer
                .create(context, raw.sound2)
                .start()
            delay(1000)
            selected = !selected
        }
    }

    Button(
        onClick = { selected = !selected
            if(!redPressed) combinacion += "1"
            conteo-- },
        enabled = !selected,
        modifier = Modifier
            .width(180.dp)
            .height(170.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkRed,
            disabledBackgroundColor = Color.Red)
    ) {
        Text("Rojo")
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BotonAzul(context: Context?){
    var selected by remember { mutableStateOf(false) }
    //var bluePressed1 by remember { mutableStateOf(bluePressed) }

    LaunchedEffect(key1 = bluePressed){
        if(bluePressed)
        {
            selected = !selected
            bluePressed = false
        }
    }

    LaunchedEffect(key1 = selected)
    {
        if(selected) {
            MediaPlayer
                .create(context, raw.sound3)
                .start()
            delay(1000)
            selected = !selected
        }
    }

    Button(
        onClick = { selected = !selected
            if(!bluePressed) combinacion += "2"
            conteo-- },
        enabled = !selected,
        modifier = Modifier
            .width(180.dp)
            .height(170.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue,
            disabledBackgroundColor = Color.Blue)
    ) {
        Text("Azul")
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BotonAmarillo(context: Context?){
    var selected by remember { mutableStateOf(false) }
    //var yellowPressed1 by remember { mutableStateOf(yellowPressed) }

    LaunchedEffect(key1 = yellowPressed){
        if(yellowPressed)
        {
            selected = !selected
            yellowPressed = false
        }
    }

    LaunchedEffect(key1 = selected)
    {
        if(selected) {
            MediaPlayer
                .create(context, raw.sound4)
                .start()
            delay(1000)
            selected = !selected
        }
    }

    Button(
        onClick = { selected = !selected
            if(!yellowPressed) combinacion += "3"
            conteo--},
        enabled = !selected,
        modifier = Modifier
            .width(180.dp)
            .height(170.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkYellow,
            disabledBackgroundColor = Color.Yellow)
    ) {
        Text("Amarillo")
    }
}

@Composable
fun Iniciar(){
    var blnIniciar by remember { mutableStateOf(false) }
    //val onClick by rememberUpdatedState(onClick)

    LaunchedEffect(key1 = blnIniciar)
    {
        if(blnIniciar)
        {
            //combinacionCorrecta = SacarCombinaciones(4)
            ReproducirCombinacion("0")
            delay(500)
            blnIniciar = !blnIniciar
        }
    }

    Button(onClick = {
        blnIniciar = !blnIniciar
    }, enabled = !blnIniciar,
        modifier = Modifier
            .width(90.dp)
            .height(45.dp)
    ) {
        Text(text = "Iniciar")
    }
}

suspend fun ReproducirCombinacion(comb: String) {
    for (i in comb) {
        when (i) {
            '0' -> { greenPressed = true; }
            '1' -> { redPressed = true; }
            '2' -> { bluePressed = true; }
            '3' -> { yellowPressed = true; }
        }
        delay(1500)
    }
}

fun SacarCombinaciones(lvl : Int) : String{
    var combinacion : String = ""
    when(lvl){
        1 -> combinacion = combinacionesNvl1[Random.nextInt(combinacionesNvl1.size)]
        2 -> combinacion = combinacionesNvl2[Random.nextInt(combinacionesNvl2.size)]
        3 -> combinacion = combinacionesNvl3[Random.nextInt(combinacionesNvl3.size)]
        4-> combinacion = combinacionesNvl4[Random.nextInt(combinacionesNvl4.size)]
    }
    return combinacion
}

@Composable
fun Botones(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            BotonVerde(context = LocalContext.current)
            BotonRojo(context = LocalContext.current)
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            BotonAzul(context = LocalContext.current)
            BotonAmarillo(context = LocalContext.current)
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Box(modifier = Modifier
                .background(Color.White)
                .height(15.dp))
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Iniciar()
        }
    }
}
*/