package com.example.simondiceapp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
class TrapeziumShape(private val topWidth:Dp, private val bottomWidth: Dp, private val height:Dp):Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val topLeft = Offset(x = size.width / 2 - topWidth.value / 2, y = 0f)
            val topRight = Offset(x = size.width / 2 + topWidth.value / 2, y = 0f)
            val bottomLeft = Offset(x = size.width / 2 - bottomWidth.value / 2, y = height.value)
            val bottomRight = Offset(x = size.width / 2 + bottomWidth.value / 2, y = height.value)
            val cornerRadius = 10f
            val arcWith = bottomWidth.value - cornerRadius*2
            val arcTop = height.value - 10f

            moveTo(topLeft.x,topLeft.y)

            lineTo(topRight.x,topRight.y)
            lineTo(bottomRight.x, bottomRight.y)
            lineTo(bottomLeft.x, bottomLeft.y)

            close()
        }
        return Outline.Generic(path)
    }
}