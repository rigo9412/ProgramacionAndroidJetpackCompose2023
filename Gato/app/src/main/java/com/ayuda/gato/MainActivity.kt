package com.ayuda.gato

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ayuda.gato.ui.theme.GatoTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


var turno = mutableStateOf(true)
var cell = mutableStateOf(0)
var game = mutableStateOf( mutableListOf<Int> () )

var X0 = mutableStateOf(mutableListOf<String>("X","","","","","","","",""))

var P1 = mutableStateOf(mutableListOf<Int>())
var P2 = mutableStateOf(mutableListOf<Int>())

var START = mutableStateOf("Start")

var isGaming = mutableStateOf(false)

var WIN = mutableStateOf(false)

var win1 = arrayOf(1,2,3)
var win2 = arrayOf(4,5,6)
var win3 = arrayOf(7,8,9)
var win4 = arrayOf(1,4,7)
var win5 = arrayOf(2,5,8)
var win6 = arrayOf(3,6,9)
var win7 = arrayOf(1,5,9)
var win8 = arrayOf(3,5,5)

var arrays = arrayOf(win1,win2,win3,win4,win5,win6,win7,win8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect( WIN.value ){

            }
            GatoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "GATO", fontSize = 60.sp)
                    }

                    Gato(triggerButton = { triggerButton(0) })
                    if (!isGaming.value){
                        Button(onClick = { isGaming.value = true; START.value = ""},
                            colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color.Transparent)) {
                            Text(text = START.value , fontSize = 50.sp)
                        }
                    }

                }
            }
        }
    }
}

fun checkWin(PA: Array<Int>){
    var valid = 0
    for ( i in arrays ){
        for ( j in PA){
            if ( j == i[0] || j == i[1] || j == i[2]){
                valid++
            }
        }
    }
    if (valid == 3){
        WIN.value = true
    }
    if ( PA.size == 5){
        //Empate
    }
}
fun triggerButton(butt: Int){
    if (turno.value){
        println("TURNO X")
        X0.value[butt-1] = "X"
    }
    else{

        X0.value[butt] = "O"
        println(X0.value[butt])
    }
    if (turno.value){
        P1.value.add(butt)
    }
    else{
        P2.value.add(butt)
    }
    turno.value = !turno.value
}

@Composable
fun Gato(triggerButton:() -> Unit) {
    if (isGaming.value){
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Column {
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(0) }
                ){
                        Text(text = X0.value[0], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(2) }
                ){
                    Text(text = X0.value[1], fontSize = 50.sp )
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(3) }){
                    Text(text = X0.value[2], fontSize = 50.sp)
                }
            }
            Column {
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(4) }){
                    Text(text = X0.value[3], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(5) }){
                    Text(text = X0.value[4], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(6) }){
                    Text(text = X0.value[5], fontSize = 50.sp)
                }
            }
            Column {
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(7) }){
                    Text(text = X0.value[6], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(8) }){
                    Text(text = X0.value[7], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(9) }){
                    Text(text = X0.value[8], fontSize = 50.sp)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GatoTheme {
    }
}