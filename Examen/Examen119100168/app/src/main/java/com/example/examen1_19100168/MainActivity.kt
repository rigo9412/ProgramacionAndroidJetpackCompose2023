package com.example.examen1_19100168

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen1_19100168.domain.Action
import com.example.examen1_19100168.ui.theme.Examen119100168Theme

var _listActions1 = mutableListOf<Action>()
var _listActions2 = mutableListOf<Action>()
var _maxSteps = 1
//var regitro1 by remember { mutableStateOf(0) }
//var registro2 by remember { mutableStateOf(0) }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            Examen119100168Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Juego()
                }
            }
        }
    }
}


@Composable
fun Juego(    actionPlayer: Action,
    onClick: (action: Action) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFEB3B))

    ) {
        Text(text = "Gato")
        Text(text = "Puntuaje")
        Text(text = "Turno de jugador")
        Pad(actionPlayer,
            onClick)
    }
}


@Composable
fun Pad(    action: Action?,
            onClick: (action: Action) -> Unit) {
    var context = LocalContext.current
    var x = ""

    Row() {
        ButtonAction(
            on = action == Action.Casilla1,
            action = Action.Casilla1,
            onClick = onClick
        /*
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
                .clickable(_listActions1.add(Action.Casilla1)) {
                  //  onClick = {onClick()}
                }
        */
        )
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        ) {

        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        ) {

        }

    }
    Row() {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        )

        {
            Text(text = "X", fontSize = 40.sp)
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        ) {

        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        ) {

        }
    }
    Row() {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        )

        {
            Text(text = "X", fontSize = 40.sp)
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        ) {

        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .background(Color(0xFFFF0000))
        ) {

        }
    }

}


@Composable
fun ButtonAction(
    on: Boolean,
    action: Action,
    onClick: (Action) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(

        modifier = Modifier
            .padding(10.dp)
            .size(60.dp)
            .background(Color(0xFFFF0000))
            /*.background(
                if (on) colorOn else colorOff
            )*/
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onClick(action)
            },
        contentAlignment = Alignment.Center
    ) {
        //Text(text = action, fontWeight = FontWeight(900), color = Color.White)
    }
}

