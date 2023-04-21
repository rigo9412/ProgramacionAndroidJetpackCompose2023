package com.example.examenjuegogato

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenjuegogato.ui.theme.ExamenJuegoGatoTheme
import com.example.examenjuegogato.ui.theme.rosaPastel
import com.example.examenjuegogato.ui.theme.azulPastel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val interactionSource = remember { MutableInteractionSource() }
            //movimiento para el cambio del jugador
            var movimiento by remember  { mutableStateOf(1) }
            var textoCuadro1 by remember  { mutableStateOf("")}
            var textoCuadro2 by remember  { mutableStateOf("")}
            var textoCuadro3 by remember  { mutableStateOf("")}
            var textoCuadro4 by remember  { mutableStateOf("")}
            var textoCuadro5 by remember  { mutableStateOf("")}
            var textoCuadro6 by remember  { mutableStateOf("")}
            var textoCuadro7 by remember  { mutableStateOf("")}
            var textoCuadro8 by remember  { mutableStateOf("")}
            var textoCuadro9 by remember  { mutableStateOf("")}

            var bandera=true

            var desabilitarBoton1 by remember  { mutableStateOf(true)}
            var desabilitarBoton2 by remember  { mutableStateOf(true)}
            var desabilitarBoton3 by remember  { mutableStateOf(true)}
            var desabilitarBoton4 by remember  { mutableStateOf(true)}
            var desabilitarBoton5 by remember  { mutableStateOf(true)}
            var desabilitarBoton6 by remember  { mutableStateOf(true)}
            var desabilitarBoton7 by remember  { mutableStateOf(true)}
            var desabilitarBoton8 by remember  { mutableStateOf(true)}
            var desabilitarBoton9 by remember  { mutableStateOf(true)}

            var desabilitarBotonInicio by remember  { mutableStateOf(true)}
            var desabilitarBotonReinicio by remember  { mutableStateOf(false)}

            var ganador by remember { mutableStateOf("") }
            val lightBlue = Color(0xffd8e6ff)
            var colorJugador2 by remember { mutableStateOf(false) }
            var colorJugador3 by remember { mutableStateOf(false) }
            var colorJugador1 by remember { mutableStateOf(false) }
            var colorJugador4 by remember { mutableStateOf(false) }
            var colorJugador5 by remember { mutableStateOf(false) }
            var colorJugador6 by remember { mutableStateOf(false) }
            var colorJugador7 by remember { mutableStateOf(false) }
            var colorJugador8 by remember { mutableStateOf(false) }
            var colorJugador9 by remember { mutableStateOf(false) }

            var jugador2 by remember { mutableStateOf("") }
            var jugador1 by remember { mutableStateOf("") }

            // accion de los jugadores
            var accionJugador1 by remember {mutableStateOf("")}
            var accionJugador2 by remember {mutableStateOf("")}

            //guardar lo
           var  ColorDefault=Color.Magenta

            var juegoIniciado by remember { mutableStateOf(false) }

            var marcadorDeInicio by remember { mutableStateOf(1) }

            var turno by remember { mutableStateOf(9) }

            ExamenJuegoGatoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(modifier=Modifier.padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top) {
                            Text(text = "Gato", fontSize = 30.sp, color = Color.Black, fontFamily = FontFamily.Monospace)
                        }
                        Row(modifier=Modifier.padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top,
                        ) {
                            Text(text = "$ganador", fontSize = 30.sp, color = Color.Black, fontFamily = FontFamily.Monospace)
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            //verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(100.dp)
                                .width(350.dp)


                        ){
                            OutlinedTextField(
                                value = jugador1,
                                onValueChange = { jugador1 = it },
                                label = { Text("Jugador 1") },
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(60.dp),
                                placeholder = { Text("Jugador 1", color = Color.Black) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = lightBlue)


                            )
                            OutlinedTextField(
                                value = jugador2,
                                onValueChange = { jugador2 = it },
                                label = { Text("Jugador 2") },
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(60.dp),
                                placeholder = { Text("Jugador 2", color = Color.Black) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = lightBlue)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            //verticalAlignment = Alignment.Top,
                            ) {
                            Button(

                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno- 1

                                    if(movimiento==1){
                                        textoCuadro1="X"
                                        movimiento=2
                                        accionJugador1 += "1"
                                        desabilitarBoton1 = false
                                        colorJugador1 = true


                                    }else{
                                        textoCuadro1="O"
                                        movimiento=1
                                        accionJugador2 += "1"
                                        desabilitarBoton1 = false
                                    }
                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador1) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton1

                            ){
                                if (bandera==true){
                                    Text(text = textoCuadro1, fontWeight = FontWeight(900), color = Color.Black)

                                }


                            }
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1
                                    if(movimiento==1){
                                        textoCuadro2="X"
                                        movimiento=2
                                        accionJugador1 += "2"
                                        desabilitarBoton2 = false
                                        colorJugador2 = true


                                    }else{
                                        textoCuadro2="O"
                                        movimiento=1
                                        accionJugador2 += "2"
                                        desabilitarBoton2 = false
                                    }

                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador2) rosaPastel else azulPastel),
                                enabled = if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton2

                            ){
                                if (bandera==true){
                                    Text(text = textoCuadro2, fontWeight = FontWeight(900), color = Color.Black)

                                }
                            }
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1
                                    if(movimiento==1){
                                        textoCuadro3="X"
                                        movimiento=2
                                        accionJugador1 += "3"
                                        desabilitarBoton3 = false
                                        colorJugador3 = true


                                    }else{
                                        textoCuadro3="O"
                                        movimiento=1
                                        accionJugador2 += "3"
                                        desabilitarBoton3 = false
                                    }

                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador3) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton3 ) {
                                if (bandera==true){
                                    Text(text = textoCuadro3, fontWeight = FontWeight(900), color = Color.Black)

                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top) {
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1
                                    if(movimiento==1){
                                        textoCuadro4="X"
                                        movimiento=2
                                        accionJugador1 += "4"
                                        desabilitarBoton4 = false
                                        colorJugador4 = true


                                    }else{
                                        textoCuadro4="O"
                                        movimiento=1
                                        accionJugador2 += "4"
                                        desabilitarBoton4 = false
                                    }
                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador4) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton4 ) {
                                if (bandera==true){
                                    Text(text = textoCuadro4, fontWeight = FontWeight(900), color = Color.Black)

                                }
                            }
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1
                                    if(movimiento==1){
                                        textoCuadro5="X"
                                        movimiento=2
                                        accionJugador1 += "5"
                                        desabilitarBoton5 = false
                                        colorJugador5 = true


                                    }else{
                                        textoCuadro5="O"
                                        movimiento=1
                                        accionJugador2 += "5"
                                        desabilitarBoton5 = false
                                    }

                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador5) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton5 ) {
                                if (bandera==true){
                                    Text(text = textoCuadro5, fontWeight = FontWeight(900), color = Color.Black)

                                }
                            }
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1
                                    if(movimiento==1){
                                        textoCuadro6="X"
                                        movimiento=2
                                        accionJugador1 += "6"
                                        desabilitarBoton6 = false
                                        colorJugador6 = true


                                    }else{
                                        textoCuadro6="O"
                                        movimiento=1
                                        accionJugador2 += "6"
                                        desabilitarBoton6 = false
                                    }

                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador6) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton6 ) {
                                if (bandera==true){
                                    Text(text = textoCuadro6, fontWeight = FontWeight(900), color = Color.Black)

                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top) {
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1

                                    if(movimiento==1){
                                        textoCuadro7="X"
                                        movimiento=2
                                        accionJugador1 += "7"
                                        desabilitarBoton7 = false
                                        colorJugador7 = true


                                    }else{
                                        textoCuadro7="O"
                                        movimiento=1
                                        accionJugador2 += "7"
                                        desabilitarBoton7 = false
                                    }

                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1=""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador7) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton7 ) {
                                if (bandera==true){
                                    Text(text = textoCuadro7, fontWeight = FontWeight(900), color = Color.Black)

                                }
                            }
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1

                                    if(movimiento==1){
                                        textoCuadro8="X"
                                        movimiento=2
                                        accionJugador1 += "8"
                                        desabilitarBoton8 = false
                                        colorJugador8 = true


                                    }else{
                                        textoCuadro8="O"
                                        movimiento=1
                                        accionJugador2 += "8"
                                        desabilitarBoton8 = false
                                    }

                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador8) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton8) {
                                if (bandera==true){
                                    Text(text = textoCuadro8, fontWeight = FontWeight(900), color = Color.Black)

                                }

                            }
                            Button(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                                    .background(ColorDefault),
                                onClick = {
                                    turno-1
                                    if(movimiento==1){
                                        textoCuadro9="X"
                                        movimiento=2
                                        accionJugador1 += "9"
                                        desabilitarBoton9 = false
                                        colorJugador9 = true


                                    }else{
                                        textoCuadro9="O"
                                        movimiento=1
                                        accionJugador2 += "9"
                                        desabilitarBoton9 = false
                                    }

                                    ganador = Combinaciones(jug1 = accionJugador1, jug2 = accionJugador2, nomJug1 = jugador1, nomJug2 = jugador2)
                                    if(ganador != ""){

                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                    else if (turno == 0)
                                    {
                                        ganador = "Empate"
                                        accionJugador1 = ""
                                        accionJugador2=""
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = if(colorJugador9) rosaPastel else azulPastel),
                                enabled =  if (marcadorDeInicio==1) juegoIniciado else desabilitarBoton9) {
                                if (bandera==true){
                                    Text(text = textoCuadro9, fontWeight = FontWeight(900), color = Color.Black)

                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(250.dp)
                                .width(310.dp)
                                ) {
                            OutlinedButton(
                                modifier = Modifier
                                    .width(130.dp)
                                    .height(60.dp),
                                onClick = {marcadorDeInicio= 0
                                    juegoIniciado=false
                                    desabilitarBotonInicio=false
                                    desabilitarBotonReinicio=true
                                }, border = BorderStroke(
                                    width = 4.dp,
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFF42A5F5),
                                            Color(0xFFFFA726)
                                        )
                                    )
                                ),

                               enabled = desabilitarBotonInicio,
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color.DarkGray)

                            ) {
                                Text("Iniciar", color = Color(0xFF03A9F4))
                            }
                            OutlinedButton(
                                modifier = Modifier
                                    .width(130.dp)
                                    .height(60.dp),
                                onClick = {marcadorDeInicio= 1
                                    juegoIniciado=false
                                    desabilitarBotonInicio=true
                                    desabilitarBotonReinicio=false

                                    accionJugador1 = ""
                                    accionJugador2=""
                                    ganador=""
                                    movimiento=1

                                    textoCuadro1=""
                                    textoCuadro2=""
                                    textoCuadro3=""
                                    textoCuadro4=""
                                    textoCuadro5=""
                                    textoCuadro6=""
                                    textoCuadro7=""
                                    textoCuadro8=""
                                    textoCuadro9=""

                                    colorJugador1=false
                                    colorJugador2=false
                                    colorJugador3=false
                                    colorJugador4=false
                                    colorJugador5=false
                                    colorJugador6=false
                                    colorJugador7=false
                                    colorJugador8=false
                                    colorJugador9=false
                                }, border = BorderStroke(
                                    width = 4.dp,
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFF42A5F5),
                                            Color(0xFFFFA726)
                                        )
                                    )
                                ),

                                enabled = desabilitarBotonReinicio,
                                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color.DarkGray)

                            ) {
                                Text("Reiniciar", color = Color(0xFF03A9F4))
                            }
                        }
                    }
                }


            }
        }
    }
}


fun Combinaciones(jug1: String, jug2:String, nomJug1:String,nomJug2:String): String{
    //formas de ganar horizontamente
    var formaGanar1 = "123"
    var formaGanar2 = "456"
    var formaGanar3 = "789"
    var formaGanar9 = "741"
    var formaGanar10 = "852"
    var formaGanar11 = "963"
    var formaGanar12 = "693"
    //formas de ganar diagonalmente
    var formaGanar4 = "159"
    var formaGanar5 = "357"
    //formas de ganar verticalmente
    var formaGanar6 = "147"
    var formaGanar7 = "258"
    var formaGanar8 = "369"

    if(jug1 == formaGanar1 || jug1== formaGanar2 || jug1== formaGanar3 || jug1== formaGanar4  || jug1== formaGanar5 || jug1== formaGanar6 || jug1== formaGanar7  || jug1== formaGanar8
        || jug1== formaGanar9|| jug1== formaGanar10 || jug1== formaGanar11 || jug1== formaGanar12)
    {
        return "Ganó $nomJug1"
    }
    if(jug2== formaGanar1 || jug2== formaGanar2 || jug2== formaGanar3 || jug2== formaGanar4 || jug2== formaGanar5 || jug2== formaGanar6 || jug2== formaGanar7|| jug2== formaGanar8
        || jug2== formaGanar9|| jug2== formaGanar10|| jug2== formaGanar11|| jug2== formaGanar12)
    {
        return "Ganó $nomJug2"
    }
    return ""


}





@Composable
fun textoJugador2() {
    var jugador2 by remember {
        mutableStateOf("")
    }
    TextField(
        value = jugador2,
        onValueChange = { jugador2 = it },
        label = { Text("Jugador 2") }
    )
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExamenJuegoGatoTheme {

    }
}

