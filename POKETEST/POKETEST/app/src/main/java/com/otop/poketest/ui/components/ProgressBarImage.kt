package com.otop.poketest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.otop.poketest.ui.theme.grisTrans


@Composable
fun ImageWithProgressBar(
    painter: Painter,
    progress: Float,
    modifier: Modifier = Modifier,
    isComplete: Boolean,
    contentDescription: String? = null,
    progressColor: Color = Color.Gray,
) {
    Box(modifier = Modifier.size(200.dp)) {
        // Imagen
        if (isComplete){
            Image(
                painter = painter,
                contentDescription = "Trainer Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }
        else{
            Image(
                painter = painter,
                contentDescription = "Trainer Profile Picture",
                colorFilter = ColorFilter.tint(grisTrans),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }


        // Barra de progreso
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterEnd)
        )
    }
}
