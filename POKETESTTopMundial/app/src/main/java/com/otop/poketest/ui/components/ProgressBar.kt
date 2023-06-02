package com.otop.poketest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    value: Int,
    max: Int,
    modifier: Modifier = Modifier,
    color: Color
) {

    val percentage = (value.toFloat() / max.toFloat()).coerceIn(0f, 1f)

    Column() {
        Text(text = "Progresion de Pokemons descubiertos", fontWeight = FontWeight.ExtraBold,
            color = color)
        LinearProgressIndicator(
            modifier = modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            progress = percentage,
            color = Color.Blue,
        )
    }
}
