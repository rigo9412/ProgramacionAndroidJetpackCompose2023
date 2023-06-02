package com.almy.simondice.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.almy.simondice.ui.theme.*



@Composable
fun CustomButton(text:  String, color: Color,enabled: Boolean,onButtonSelected: () -> Unit){
    Button(
        onClick = {onButtonSelected()},
        modifier = Modifier.size(150.dp,200.dp).padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = color,
            contentColor = Color.Transparent
        ),
        enabled = enabled
    ){
        Text(text = text/*,color = if (text == "")  Color.White else Color.Black*/)
    }
}


@Composable
fun InitButton(text: String,modifier: Modifier,onButtonSelected: () -> Unit){
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