package com.example.simondiceviewmodel

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.simondiceviewmodel.screeens.*
import com.example.simondiceviewmodel.ui.theme.*
import kotlinx.coroutines.delay


val brush = Brush.horizontalGradient(
    listOf(moradoPastel, azulPastel)
)




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val gameViewModel by viewModels<GameViewModel>()
        setContent{
            ScreenGame(gameViewModel = gameViewModel)
//            SimonDiceViewModelTheme
//
//
//            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SimonDiceViewModelTheme {
//        val perdAccion = Accion.prBotonRojo
//        val game = Juego()
//        SimonDice(game, null,perdAccion, false, onClick = {})
//        records()
//
//    }
//}