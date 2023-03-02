package com.example.simondice19100197.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
//Sin usar
class CircleShape(
    val width: Dp,
    val height: Dp) :
    Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val innerWidth = width * 0.8f
            val innerHeight = height * 0.8f
            val topLeft = Offset(x = (width.value - innerWidth.value) / 2, y = (height.value - innerHeight.value) / 2)
            val topRight = Offset(x = (width.value + innerWidth.value) / 2, y = (height.value - innerHeight.value) / 2)
            val bottomLeft = Offset(x = (width.value - innerWidth.value) / 2, y = (height.value + innerHeight.value) / 2)
            val bottomRight = Offset(x = (width.value + innerWidth.value) / 2, y = (height.value + innerHeight.value) / 2)

            moveTo(topLeft.x, topLeft.y)
            lineTo(topRight.x, topRight.y)
            lineTo(bottomRight.x, bottomRight.y)
            lineTo(bottomLeft.x, bottomLeft.y)
            close()

        }
        return Outline.Generic(path)
    }

}