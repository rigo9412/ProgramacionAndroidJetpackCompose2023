package com.almy.gato.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.almy.gato.ui.theme.azulClaro
import com.almy.gato.ui.theme.azulOscuro
import com.almy.gato.ui.theme.rojoClaro
import com.almy.gato.ui.theme.verdeClaro


@Composable
fun CustomButton(text:  String,isVisible:Boolean,enabled: Boolean, isAdivinated: Boolean, onButtonSelected: () -> Unit){

    val caracterAMostrar =  if (isVisible) text else "?"

    Button(
        onClick = {onButtonSelected()},
        modifier = Modifier
            .size(150.dp, 220.dp)
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = azulOscuro,
            disabledBackgroundColor = if(caracterAMostrar == "X") verdeClaro else if(caracterAMostrar == "O") azulClaro else Color.Gray,
            contentColor = Color.Black
        ),
        enabled = if (isAdivinated) false else enabled

    ){
        Text(text = if (isVisible) caracterAMostrar else if (isAdivinated) caracterAMostrar else "?",color = Color.Black)
    }
}


@Composable
fun IniciarButton(text: String, modifier: Modifier,onButtonSelected: () -> Unit){
    Button(
        onClick = {onButtonSelected()},
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = rojoClaro,
            contentColor = Color.Black
        ),
    ){
        Text(text = text)
    }
}

