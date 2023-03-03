package com.ayuda.gato

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ayuda.gato.ui.theme.GatoTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


var turno = mutableStateOf(true)

var empate = mutableStateOf(false)

var P1 = mutableStateOf(mutableListOf<Int>())
var P2 = mutableStateOf(mutableListOf<Int>())

var START = mutableStateOf("Start")

var isGaming = mutableStateOf(false)

var WIN = mutableStateOf(false)
var X0 = mutableStateOf(mutableListOf<String>("","","","","","","","",""))

var WinX = mutableStateOf(false)
var WinO = mutableStateOf(false)

var win1 = arrayOf(0,1,2)
var win2 = arrayOf(3,4,5)
var win3 = arrayOf(6,7,8)
var win4 = arrayOf(0,3,6)
var win5 = arrayOf(1,4,7)
var win6 = arrayOf(2,5,8)
var win7 = arrayOf(0,4,8)
var win8 = arrayOf(2,4,6)

var arrays = arrayOf(win1,win2,win3,win4,win5,win6,win7,win8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var reload by remember{mutableStateOf(true)}

            GatoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "GATO " + reload, fontSize = 60.sp)
                    }

                    Gato(reload = {reload = it}, X0.value)
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

fun checkWin(PA: MutableList<Int>,Win: String){
    var valid = 0
    for ( i in arrays ){
        for ( j in PA){
            if ( j == i[0] || j == i[1] || j == i[2]){
                valid++
            }
            if (valid == 3){
                if (Win == "X"){
                    WinX.value = true
                }
                else{
                    WinO.value = true
                }
            }
        }
        valid = 0
    }
    if ( PA.size == 5){
        empate.value = true
    }
}
fun triggerButton(butt: Int,reload: (Boolean) -> Unit){
    if (turno.value){
        X0.value[butt] = "X"
    }
    else{
        X0.value[butt] = "O"
    }
    if (turno.value){
        P1.value.add(butt)
        checkWin(P1.value,"X")
    }
    else{
        P2.value.add(butt)
        checkWin(P2.value,"O")
    }
    turno.value = !turno.value
    reload(turno.value)
}

@Composable
fun Gato(reload: (Boolean) -> Unit, X0: MutableList<String>) {
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
        if (empate.value){
            Text(text = "Empate", fontSize = 40.sp)
        }
        if (WinX.value){
            Text(text = "Ganaron las XXX", fontSize = 40.sp)
        }
        else if (WinO.value){
            Text(text = "Ganaron las Hoyos", fontSize = 40.sp)
        }
    }
    if (isGaming.value){
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Column {
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(0, reload)}
                ){
                    Text(text = X0[0], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(3, reload) }
                ){
                    Text(text = X0[3], fontSize = 50.sp )
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(6, reload) }){
                    Text(text = X0[6], fontSize = 50.sp)
                }
            }
            Column {
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(1, reload) }){
                    Text(text = X0[1], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(4, reload) }){
                    Text(text = X0[4], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(7, reload) }){
                    Text(text = X0[7] , fontSize = 50.sp)
                }
            }
            Column {
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(2, reload) }){
                    Text(text = X0[2], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(5, reload) }){
                    Text(text = X0[5], fontSize = 50.sp)
                }
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(width = 1.dp, color = Color.White)
                        .background(Color.Gray)
                        .clickable { triggerButton(8, reload) }){
                    Text(text = X0[8], fontSize = 50.sp)
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