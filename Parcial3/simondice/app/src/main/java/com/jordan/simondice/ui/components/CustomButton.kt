package com.jordan.simondice.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(text: String, modifier: Modifier, startGameState: Boolean, onClick: () -> Unit) {
    TextButton(modifier = modifier
        .padding(10.dp)
        .height(80.dp)
        .fillMaxWidth(),
        border = BorderStroke(
            width = 2.dp, brush = Brush.horizontalGradient(
                listOf(
                    Color.Cyan, Color.Yellow
                )
            )
        ),

        enabled = !startGameState,
        onClick = { onClick() }) {
        Text(text = text, color = Color.White, fontSize = 28.sp)
    }
}
