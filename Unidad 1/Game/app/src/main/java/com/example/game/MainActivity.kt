package com.example.game

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game.sections.*
import com.example.game.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var greenOn by remember { mutableStateOf(false) }
            var redOn by remember { mutableStateOf(false) }
            var blueOn by remember { mutableStateOf(false) }
            var yellowOn by remember { mutableStateOf(false) }
            var selected1 by remember { mutableStateOf(false) }
            var selected2 by remember { mutableStateOf(false) }
            var selected3 by remember { mutableStateOf(false) }
            var selected4 by remember { mutableStateOf(false) }
            var startOn by remember { mutableStateOf(false) }
            var disableInteraction by remember { mutableStateOf(false) }
            var simonTurn by remember { mutableStateOf(false) }
            var playerTurn by remember { mutableStateOf(false) }
            var playerTurnFinished by remember { mutableStateOf(false) }
            var generate by remember { mutableStateOf(false) }
            var context: Context? = LocalContext.current

            var combinacionCorrecta by remember { mutableStateOf("") }
            var combinacionPresionada by remember { mutableStateOf("") }
            //var puntos by remember { mutableStateOf(0) }
            var nivel by remember { mutableStateOf(1) }
            var msj by remember { mutableStateOf("") }


            GameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(key1 = greenOn){
                        if(greenOn)
                        {
                            selected1 = !selected1
                            greenOn = !greenOn
                        }
                    }

                    LaunchedEffect(key1 = selected1)
                    {
                        if(selected1) {
                            MediaPlayer
                                .create(context, R.raw.sound1)
                                .start()
                            delay(1000)
                            selected1 = !selected1
                        }
                    }
                    LaunchedEffect(key1 = redOn){
                        if(redOn)
                        {
                            selected2 = !selected2
                            redOn = !redOn
                        }
                    }

                    LaunchedEffect(key1 = selected2)
                    {
                        if(selected2) {
                            MediaPlayer
                                .create(context, R.raw.sound2)
                                .start()
                            delay(1000)
                            selected2 = !selected2
                        }
                    }

                    LaunchedEffect(key1 = blueOn){
                        if(blueOn)
                        {
                            selected3 = !selected3
                            blueOn = !blueOn
                        }
                    }

                    LaunchedEffect(key1 = selected3)
                    {
                        if(selected3) {
                            MediaPlayer
                                .create(context, R.raw.sound3)
                                .start()
                            delay(1000)
                            selected3 = !selected3
                        }
                    }

                    LaunchedEffect(key1 = yellowOn){
                        if(yellowOn)
                        {
                            selected4 = !selected4
                            yellowOn = !yellowOn
                        }
                    }

                    LaunchedEffect(key1 = selected4)
                    {
                        if(selected4) {
                            MediaPlayer
                                .create(context, R.raw.sound4)
                                .start()
                            delay(1000)
                            selected4 = !selected4
                        }
                    }
                    LaunchedEffect(key1 = generate)
                    {
                        if(generate)
                        {
                            combinacionCorrecta = GenerarCombinacion(nivel)
                            simonTurn = !simonTurn
                            generate = !generate
                        }
                    }

                    LaunchedEffect(key1 = simonTurn)
                    {
                        if(simonTurn)
                        {
                            msj = "Simon dice..."
                            for (i in combinacionCorrecta){
                                when(i){
                                    '0' -> { greenOn = true; }
                                    '1' -> { redOn = true; }
                                    '2' -> { blueOn = true; }
                                    '3' -> { yellowOn = true; }
                                }
                                delay(1500)
                            }
                            playerTurn = !playerTurn
                            msj = "¡Tu turno!"
                            simonTurn = !simonTurn
                        }
                    }

                    LaunchedEffect(key1 = playerTurnFinished)
                    {
                        if(playerTurnFinished)
                        {
                            if(combinacionPresionada == combinacionCorrecta)
                            {
                                msj = SacarFelicitacion()
                                delay(500)
                                nivel++
                                combinacionPresionada = ""
                                delay(1000)
                                generate = !generate
                                playerTurnFinished = !playerTurnFinished
                            }
                            else{
                                msj = "Vuelve a intentarlo..."
                                delay(1000)
                                startOn = !startOn
                                combinacionPresionada = ""; combinacionCorrecta = ""
                                nivel = 1
                                playerTurnFinished = !playerTurnFinished
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(modifier=Modifier
                            .fillMaxWidth()
                            .padding(19.dp, 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween)
                        {
                            Text("$msj", fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
                            Text("  Nivel: $nivel", fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            /*.gesturesDisabled(disableInteraction)*/,
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = {
                                    selected1 = !selected1
                                    if(playerTurn)
                                    {
                                        combinacionPresionada += "0"
                                    }
                                    if( (combinacionPresionada.length > 0) && (combinacionPresionada.length == combinacionCorrecta.length))
                                    {
                                        playerTurn = !playerTurn
                                        playerTurnFinished = !playerTurnFinished
                                    }
                                          },
                                //interactionSource = interactionSource,
                                enabled = !selected1,
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(170.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = DarkGreen,
                                    disabledBackgroundColor = Color.Green)
                            ) {
                                Text("Verde")
                            }
                            Button(
                                onClick = {
                                    selected2 = !selected2
                                    if(playerTurn)
                                    {
                                        combinacionPresionada += "1"
                                    }
                                    if((combinacionPresionada.length > 0) && (combinacionPresionada.length == combinacionCorrecta.length))
                                    {
                                            playerTurn = !playerTurn
                                            playerTurnFinished = !playerTurnFinished
                                    }
                                          },
                                //interactionSource = interactionSource,
                                enabled = !selected2,
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(170.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = DarkRed,
                                    disabledBackgroundColor = Color.Red)
                            ) {
                                Text("Rojo")
                            }
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            /*.gesturesDisabled(disableInteraction)*/,
                            horizontalArrangement = Arrangement.Center){
                            Button(
                                onClick = { selected3 = !selected3
                                    if(playerTurn)
                                    {
                                        combinacionPresionada += "2"
                                    }
                                    if((combinacionPresionada.length > 0) && (combinacionPresionada.length == combinacionCorrecta.length))
                                    {
                                            playerTurn = !playerTurn
                                            playerTurnFinished = !playerTurnFinished
                                    }
                                          },
                                //interactionSource = interactionSource,
                                enabled = !selected3,
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(170.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue,
                                    disabledBackgroundColor = Color.Blue)
                            ) {
                                Text("Azul")
                            }
                            Button(
                                onClick = { selected4 = !selected4
                                    if(playerTurn)
                                    {
                                        combinacionPresionada += "3"
                                    }
                                    if((combinacionPresionada.length > 0) && (combinacionPresionada.length == combinacionCorrecta.length))
                                    {
                                        playerTurn = !playerTurn
                                        playerTurnFinished = !playerTurnFinished
                                    }
                                          },
                                //interactionSource = interactionSource,
                                enabled = !selected4,
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(170.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = DarkYellow,
                                    disabledBackgroundColor = Color.Yellow)
                            ) {
                                Text("Amarillo")
                            }
                        }
                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            Box(modifier = Modifier
                                .background(Color.White)
                                .height(15.dp))
                        }
                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly){
                            Button(onClick = {
                                startOn = !startOn
                                generate = !generate
                            }, enabled = !startOn,
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(45.dp)
                            ) {
                                Text(text = "Iniciar")
                            }

                            Button(onClick = {
                                if(startOn)
                                {
                                    startOn = !startOn
                                    generate = false
                                    combinacionCorrecta = ""
                                    combinacionPresionada = ""
                                    nivel = 1
                                    playerTurnFinished = false
                                    playerTurn = false
                                    simonTurn = false
                                    msj = ""
                                }
                            },
                                enabled = startOn,
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(45.dp)
                            ) {
                                Text(text = "Reset")
                            }
                        }
                    }
                }
            }
        }
    }
}

fun GenerarCombinacion(nvl: Int): String{
    var combinacion = ""
    //var valor = 0; var valorPrevio = 0
    var i = nvl
    while(i != 0)
    {
        combinacion = combinacion + Random.nextInt(4).toString()
        i--
    }
    return combinacion
}

fun SacarFelicitacion():String{
    var frases = listOf("¡Excelente!", "¡Correcto!", "¡Bien hecho!", "¡Wow!", "¡Sigue así!")
    var frase = frases[Random.nextInt(5)]
    return frase
}
/*fun Modifier.gesturesDisabled(disabled: Boolean) =
    if (disabled) {
        pointerInput(Unit) {
            awaitPointerEventScope {
                // we should wait for all new pointer events
                while (true) {
                    awaitPointerEvent(pass = PointerEventPass.Initial)
                        .changes
                        .forEach(PointerInputChange::consume)
                }
            }
        }
    } else {
        this
    }*/