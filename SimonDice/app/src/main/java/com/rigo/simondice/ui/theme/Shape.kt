package com.rigo.simondice.ui.theme

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp
import com.rigo.simondice.DonaShape
import com.rigo.simondice.TrapeziumShape

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)

)

val buttonShape = TrapeziumShape(topWidth = 160.dp, bottomWidth = 580.dp, height = 200.dp)

