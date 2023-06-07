package com.almy.gato.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun CustomText(text: String, modifier: Modifier){
    Text(
        text = text,
        fontSize = 100.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}
