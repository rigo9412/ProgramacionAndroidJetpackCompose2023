package Examen.examenTec.form

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import Examen.examenTec.ui.theme.Purple700
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip

@Composable
fun Gato(tabla: MutableList<MutableList<String>> , jugador: Boolean,cambiojugador:() -> Unit){

    Column(){
        Text(text = "Juego Del Gato", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 45.sp, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(20.dp)
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)){
            (Cuadro(0,0,tabla,jugador,cambiojugador))
            (Cuadro(0,1,tabla,jugador,cambiojugador))
            (Cuadro(0,2,tabla,jugador,cambiojugador))
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            (Cuadro(1,0,tabla,jugador,cambiojugador))
            (Cuadro(1,1,tabla,jugador,cambiojugador))
            (Cuadro(1,2,tabla,jugador,cambiojugador))
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)){
            (Cuadro(2,0,tabla,jugador, cambiojugador))
            (Cuadro(2,1,tabla,jugador, cambiojugador))
            (Cuadro(2,2,tabla,jugador, cambiojugador))
        }
    }
}

@Composable
fun Cuadro(x:Int, y:Int, tabla: MutableList<MutableList<String>>, jugador: Boolean, cambiojugador:() -> Unit) :String {
    var buttonState by remember { mutableStateOf("") }
    Button( modifier = Modifier.padding(5.dp).clip(shape = RoundedCornerShape(size = 2.dp)).background(color = Purple700),
        onClick = {
            if(tabla[x][y] == "") {
                tabla[x][y] = if (jugador) "x" else "o"
                cambiojugador()
            }
        },
    ) {
        Text(text = tabla[x][y])
    }
    return buttonState
}