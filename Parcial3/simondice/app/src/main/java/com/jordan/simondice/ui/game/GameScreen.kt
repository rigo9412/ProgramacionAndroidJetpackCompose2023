package com.jordan.simondice.ui.game

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jordan.simondice.R
import com.jordan.simondice.domain.models.Game
import com.jordan.simondice.domain.models.Player
import com.jordan.simondice.domain.models.getresponsetop.Action
import com.jordan.simondice.ui.theme.*

@Composable
fun GameScreen() {
    val context = LocalContext.current
    val vm = hiltViewModel<GameViewModel>()
    val blueAudio: MediaPlayer by remember {
        mutableStateOf(
            MediaPlayer.create(
                context, R.raw.blue
            )
        )
    }
    val redAudio: MediaPlayer by remember {
        mutableStateOf(
            MediaPlayer.create(
                context, R.raw.red
            )
        )
    }
    val yellowAudio: MediaPlayer by remember {
        mutableStateOf(
            MediaPlayer.create(
                context, R.raw.yellow
            )
        )
    }
    val greenAudio: MediaPlayer by remember {
        mutableStateOf(
            MediaPlayer.create(
                context, R.raw.green
            )
        )
    }
    val state = vm.uiState.collectAsState().value
    val data = vm.uiStateData.collectAsState().value

    LaunchedEffect(data.currentActionSimonIndex) {
        vm.GameSpeak(greenAudio,redAudio,yellowAudio,blueAudio)
    }

    LaunchedEffect(data.actionPlayer) {
        vm.PlayerPlays(data.actionPlayer,greenAudio,redAudio,yellowAudio,blueAudio)
    }

    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Status("SIMON ORDENA")
        Score(data.score,data.level)
        Pad(
            if (vm.endSpeak)
                data.actionPlayer
            else
                vm.getCurrentAction(),
            data.currentActionOn,
            enablePlay = vm.endSpeak,

            ){
            vm.onEvent(GameEvent.PressButtonEvent(it))
        }

    }
}

@Composable
fun Score(score: Int, level: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "SCORE: $score",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight(900)
        )
        Text(
            text = "LEVEL: $level",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight(900)
        )
    }
}

@Composable
fun Status(status: String) {
    Text(
        modifier = Modifier.background(Color.Cyan),
        text = status,
        color = Color.Red,
        fontSize = 24.sp

    )
}


@Composable
fun Pad(
    action: Action?, onCurrent: Boolean, enablePlay: Boolean, onClick: (action: Action) -> Unit
) {


    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_GREEN_BUTTON && onCurrent,
                colorOn = GreenOn,
                colorOff = GreenOff,
                colorText = "Verde",
                enablePlay = enablePlay,
                rotate = 135f,
                action = Action.PRESS_GREEN_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_RED_BUTTON && onCurrent,
                colorOn = RedOn,
                colorOff = RedOff,
                colorText = "Rojo",
                rotate = -135f,
                enablePlay = enablePlay,
                action = Action.PRESS_RED_BUTTON,
                onClick = onClick

            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_BLUE_BUTTON && onCurrent,
                colorOn = BlueOn,
                colorOff = BlueOff,
                colorText = "Azul",
                enablePlay = enablePlay,
                rotate = 45f,
                action = Action.PRESS_BLUE_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_YELLOW_BUTTON && onCurrent,
                colorOn = YellowOn,
                colorOff = YellowOff,
                colorText = "Amarillo",
                enablePlay = enablePlay,
                rotate = -45f,
                action = Action.PRESS_YELLOW_BUTTON,
                onClick = onClick
            )
        }
    }
}


@Composable
fun ButtonAction(
    on: Boolean,
    colorOn: Color,
    colorOff: Color,
    colorText: String,
    enablePlay: Boolean,
    rotate: Float,
    action: Action,
    onClick: (Action) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(

        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(4.dp)
            .rotate(rotate)
            .border(4.dp,colorOn, shape = buttonShape)
//            .background(if (on) colorOn else colorOff)
            .shadow(
                elevation = 30.dp,
                shape = buttonShape,
                ambientColor = Color.White,
                spotColor = Color.White
            )
            .background(
                if (on) colorOn else colorOff, buttonShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(action)
            }, contentAlignment = Alignment.Center
    ) {
//        Text(text = colorText, fontWeight = FontWeight(900), color = Color.White)
    }
}



@Composable
fun Status(game: Game, results: Player?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //TitleShadow()
        if (game.started && results == null) {
            Status(status = "JUEGO INICIADO")
        } else if (results != null) {
            Status(status = "JUEGO TERMINADO")
        }
    }
}





