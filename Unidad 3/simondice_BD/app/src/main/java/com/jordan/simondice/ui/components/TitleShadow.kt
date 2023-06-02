package com.jordan.simondice.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TitleShadow(title: String = "SIMON GAME",modifier: Modifier) {
    val offset = Offset(5.0f, 10.0f)
    Text(
        modifier= modifier,
        text = title, color = Color.Yellow, style = TextStyle(
            fontSize = 60.sp, shadow = Shadow(
                color = Color.Cyan, offset = offset, blurRadius = 6f
            )
        )
    )
}