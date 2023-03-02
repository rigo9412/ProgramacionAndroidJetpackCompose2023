package com.example.game.sections

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.game.ui.theme.DarkGreen

var blnEncendido = true
var blnSimonAct = false
var blnPlayerAct = false

@Composable
fun Boton(onClick: () -> Unit, offColor: Color, onColor: Color, text: String)
{
    val interactionSource = remember { MutableInteractionSource() }
    var encendido by remember{ mutableStateOf(blnEncendido) }

    Button(onClick = onClick,
        interactionSource = interactionSource,
        enabled = encendido,
        modifier = Modifier
            .width(180.dp)
            .height(170.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = onColor,
            disabledBackgroundColor = offColor)
    ) {
        Text(text = text)
    }
}

@Composable
fun CuatroBotones(){

}