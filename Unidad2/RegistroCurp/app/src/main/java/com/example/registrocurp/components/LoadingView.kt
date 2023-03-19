package com.example.registrocurp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingView(
    message: String = "Cargando..."
) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                strokeWidth = 6.dp,
                color = Color.Magenta,
                modifier = Modifier.then(Modifier.size(60.dp).rotate(degrees = 15f)))
            CircularProgressIndicator(
                strokeWidth = 6.dp,
                color = Color.Cyan,
                modifier = Modifier.then(Modifier.size(80.dp).rotate(degrees = 45f)))
            CircularProgressIndicator(
                strokeWidth = 6.dp,
                color = Color(255, 195, 0),
                modifier = Modifier.then(Modifier.size(100.dp).rotate(degrees = 75f)))
        }
        Text(text = message,fontWeight = FontWeight.Bold, fontSize = 17.sp)
    }
}