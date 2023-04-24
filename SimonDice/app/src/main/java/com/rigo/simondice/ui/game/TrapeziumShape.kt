package com.rigo.simondice.ui.game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class TrapeziumShape(private val topWidth: Dp, private val bottomWidth: Dp, private val height: Dp) :  Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val mSize = 2
            val topLeft = Offset(x = size.width / mSize - topWidth.value / mSize, y = 0f)
            val topRight = Offset(x = size.width / mSize + topWidth.value / mSize, y = 0f)
            val bottomLeft = Offset(x = size.width / mSize - bottomWidth.value / mSize, y = height.value)
            val bottomRight = Offset(x = size.width / mSize + bottomWidth.value / mSize, y = height.value)

            moveTo(topLeft.x, topLeft.y)
            lineTo(topRight.x, topRight.y)
            lineTo(bottomRight.x, bottomRight.y)
            lineTo(bottomLeft.x, bottomLeft.y)


            close()
        }
        return Outline.Generic(path)
    }

}

