package com.example.simondiceviewmodel.screeens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.example.simondiceviewmodel.pauseAudio
//import com.example.simondiceviewmodel.playAudio
import com.example.simondiceviewmodel.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun ScreenGame(gameViewModel: GameViewModel) {
    var state = gameViewModel.uiState.collectAsState().value
    when (state) {
        is Screens.Init -> { ScreenGame(gameViewModel = gameViewModel)}
        is Screens.Puntaciones-> {
//            ScreenTop10(top10ViewModel = )
        }
    else -> {}
    }

    var juego = gameViewModel.juego
    var resultsState = gameViewModel.resultsState
    var currentActionOn = gameViewModel.currentActionOn
    var currentActionPlayer = gameViewModel.currentActionPlayer
    var currentActionSimonIndexState = gameViewModel.currentActionSimonIndexState
    var startGameState = gameViewModel.startGameState
    var startPlayState = gameViewModel.startPlayState


    Text(
        text = "Simon Dice",
        color = Color.White,
        fontSize = 36.sp,
        fontWeight = FontWeight(900),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1

    )
    LaunchedEffect(currentActionSimonIndexState) {
        val action = juego.obtenerCurrentAction()
        println("${action.toString()} = ${juego.curIndex}")
        if (startGameState && !currentActionOn && !juego.endSpeak) {
            when (action) {
                Accion.prBotonVerde -> {}
                Accion.prBotonRojo -> {}
                Accion.prBotonAmarillo -> {}
                Accion.prBotonAzul -> {}
                else -> {
                    //NO SUENA
                }
            }
            currentActionOn = true
            delay(1000)
            currentActionOn = false
            juego.siguienteAccion()
            currentActionSimonIndexState = juego.curIndex
            when (action) {
                Accion.prBotonVerde -> {}
                Accion.prBotonRojo -> {}
                Accion.prBotonAmarillo -> {}
                Accion.prBotonAzul -> {}
                else -> {
                    //NO SUENA
                }
            }

        }
        startPlayState = juego.endSpeak
    }
    LaunchedEffect(currentActionPlayer) {
        if (startGameState && juego.endSpeak && currentActionPlayer != Accion.sinAccion && !currentActionOn) {
            when (currentActionPlayer) {
                Accion.prBotonVerde -> {}
                Accion.prBotonRojo -> {}
                Accion.prBotonAmarillo -> {}
                Accion.prBotonAzul -> {}

                else -> {
                    //NO SUENA
                }
            }
            currentActionOn = true
            delay(300)
            currentActionOn = false

            if (!juego.validateAction(currentActionPlayer)) {
                println("END GAME")
                resultsState = juego.terminar("jugador1")
                startGameState = juego.inicio

            }

            currentActionSimonIndexState = juego.curIndex
            when (currentActionPlayer) {
                Accion.prBotonVerde -> {}
                Accion.prBotonRojo -> {}
                Accion.prBotonAmarillo -> {}
                Accion.prBotonAzul -> {}
                else -> {
                    //NO SUENA
                }
            }
            currentActionPlayer = Accion.sinAccion


        }
    }

    Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        )
        SimonDice(
            game = juego,
            results = resultsState,
            actionPlayer = currentActionPlayer,
            onCurrent = currentActionOn
        ) {
            currentActionPlayer = it
        }
        Spacer(
            modifier = Modifier
                .height(35.dp)
                .fillMaxWidth()
        )


        if (!juego.inicio) {
            BotonJugar("INICIAR JUEGO", startGameState, onClick = {
                juego.empezar()
                currentActionSimonIndexState = juego.curIndex
                startGameState = juego.inicio
                resultsState = null

            })
        }

    }

}

@Composable
fun SimonDice(
    game: Juego,
    results: Jugador?,
    actionPlayer: Accion,
    onCurrent: Boolean,
    onClick: (action: Accion) -> Unit
) {
//    val brush = Brush.horizontalGradient(
//        listOf(moradoPastel, azulPastel)
//    )

    Column(
        modifier = Modifier.background(moradoPastel),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Status(game, results)
        Score(game, results)
        Recuadros(
            if (game.endSpeak) actionPlayer else game.obtenerCurrentAction(),
            onCurrent,
            enablePlay = game.endSpeak,
            onClick
        )

    }
}


//Mostrar los cuadros que van a interaccionar con el jugador
@Composable
fun Recuadros(action: Accion?, onCurrent: Boolean, enablePlay: Boolean, onClick: (action: Accion) -> Unit){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .width(320.dp)
            .height(160.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        )
        {
            Btn(
                on = action == Accion.prBotonAmarillo && onCurrent,
                colorOn = AmarilloOn,
                colorOff = AmarrilloOff,
                colorText = "Amarillo",
                enablePlay = enablePlay,
//                rotate = 135f,
                action = Accion.prBotonAmarillo,
                onClick = onClick,
                shape = CutCornerShape(5.dp)
            )


            Btn(
                on = action == Accion.prBotonAzul && onCurrent,
                colorOn = AzulOn,
                colorOff = AzulOff,
                colorText = "Azul",
                enablePlay = enablePlay,
                action = Accion.prBotonAzul,
                onClick = onClick,
                shape = CutCornerShape(5.dp)
            )

        }
        Row(modifier = Modifier
            .width(320.dp)
            .height(160.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top

        )
        {
            Btn(
                on = action == Accion.prBotonVerde && onCurrent,
                colorOn = VerdeOn,
                colorOff = VerdeOff,
                colorText = "Verde",
                enablePlay = enablePlay,
                action = Accion.prBotonVerde,
                onClick = onClick,
                shape = CutCornerShape(5.dp)
            )

            Btn(
                on = action == Accion.prBotonRojo && onCurrent,
                colorOn = RojoOn,
                colorOff = RojoOff,
                colorText = "Rojo",
                enablePlay = enablePlay,
//                rotate = -45f,
                action = Accion.prBotonRojo,
                onClick = onClick,
                shape = CutCornerShape(5.dp)
            )

        }
    }

}

@Composable
fun Btn(
    on: Boolean,
    colorOn: Color,
    colorOff: Color,
    colorText: String,
    enablePlay: Boolean,
    action: Accion,
    onClick: (Accion) -> Unit,
    shape: Shape
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(4.dp)
            .clip(shape)
            .border(
                width = 3.dp,
                brush = Brush.horizontalGradient(
                    listOf(AmarilloOn, AzulOn, VerdeOn, RojoOn)
                ),
                shape = RectangleShape
            )

            .background(
                if (on) colorOn else colorOff
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(action)
            }, contentAlignment = Alignment.Center
    ) {
        Text(text = colorText, fontWeight = FontWeight(900), color = Color.White)
    }
}


@Composable
fun Status(game: Juego, results: Jugador?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
//        TitleShadow()
//        if (game.started && results == null) {
//            Status(status = "JUEGO INICIADO")
//        } else if (results != null) {
//            Status(status = "JUEGO TERMINADO")
//        }
    }
}


@Composable
fun Score(game: Juego, player: Jugador?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (player != null) {
            Text(
                text = "SCORE: ${player.record}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "LEVEL: ${player.level}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        } else {
            Text(
                text = "SCORE: ${game.record}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "LEVEL: ${game.nivel}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        }

    }
}

@Composable
fun BotonJugar( text: String, startGameState: Boolean, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .width(200.dp)
            .height(70.dp),
        onClick = {onClick()}, border = BorderStroke(
            width = 4.dp,
            brush = Brush.horizontalGradient(
                listOf(
                    Color(0xFF42A5F5),
                    Color(0xFFFFA726)
                )
            )
        ),

        enabled = !startGameState
    ) {
        Text("JUGAR", color = Color(0xFF5C6BC0))
    }
}