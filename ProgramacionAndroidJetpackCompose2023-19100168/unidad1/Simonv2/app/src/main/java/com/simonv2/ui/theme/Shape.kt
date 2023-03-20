package com.simonv2.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import com.simonv2.TrapeziumShape

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val buttonShape = TrapeziumShape(topWidth = 160.dp, bottomWidth = 580.dp, height = 200.dp)

