package com.otop.poketest.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.BlurredEdgeTreatment.Companion.Rectangle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.otop.poketest.MainViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun PokedexCompletedView(viewModel: MainViewModel) {
    var text by mutableStateOf("")
    var country by mutableStateOf("")

    val dick = mapOf(
    "finlandia" to "\ud83c\uddeb\ud83c\uddf7",
    "dinamarca" to "\ud83c\udde9\ud83c\uddf0",
    "suiza" to "\ud83c\udde8\ud83c\udded",
    "islandia" to "\ud83c\uddee\ud83c\uddf8",
    "paises_bajos" to "\ud83c\uddf3\ud83c\udde7",
    "noruega" to "\ud83c\uddf3\ud83c\uddf4",
    "suecia" to "\ud83c\uddf8\ud83c\uddea",
    "nueva_zelanda" to "\ud83c\uddf3\ud83c\uddff",
    "austria" to "\ud83c\udde6\ud83c\uddf9",
    "luxemburgo" to "\ud83c\uddf1\ud83c\uddfa",
    "canada" to "\ud83c\udde8\ud83c\udde6",
    "singapur" to "\ud83c\uddf8\ud83c\uddff",
    "australia" to "\ud83c\udde6\ud83c\uddfa",
    "alemania" to "\ud83c\udde9\ud83c\uddea",
    "reino_unido" to "\ud83c\uddec\ud83c\udde7",
    "japon" to "\ud83c\uddef\ud83c\uddf5",
    "estados_unidos" to "\ud83c\uddfa\ud83c\uddf8",
    "belgica" to "\ud83c\udde7\ud83c\uddea",
    "irlanda" to "\ud83c\uddee\ud83c\uddf7",
    "corea_sur" to "\ud83c\uddf0\ud83c\udded"
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Has completado tu Pokédex!",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tus intentos: ${viewModel.intentos.value}",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
            TextField(value = text , onValueChange = { x:String -> text = x }, label = { Text("Nombre: ") }, )
            ComboBox( dictionary = dick , viewModel = viewModel)
            Button(onClick = { viewModel.createTrainer(text,0.0) }) {
                Text(text = "Submit")
            }
        }
    }
}