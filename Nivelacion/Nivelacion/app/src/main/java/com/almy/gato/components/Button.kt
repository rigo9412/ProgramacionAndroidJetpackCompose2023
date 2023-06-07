package com.almy.gato.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almy.gato.ui.theme.azulBoton
import com.almy.gato.ui.theme.azulClaro
import com.almy.gato.ui.theme.rojoClaro
import com.almy.gato.ui.theme.verdeClaro




@Composable
fun Iniciar(text: String,modifier: Modifier,onButtonSelected: () -> Unit){
    Button(
        onClick = {onButtonSelected()},
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
    ){
        Text(text = text)
    }
}