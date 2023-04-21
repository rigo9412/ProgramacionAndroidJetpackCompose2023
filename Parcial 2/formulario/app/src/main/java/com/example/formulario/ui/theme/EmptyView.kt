package com.example.formulario.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun EmptyView(
    action: () -> Unit,
){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(colorPantalla),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "El CURP se ha generado",
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )

        OutlinedButton(onClick = { action() }) {
            Text(text="VER CURP", color= Color.Magenta)
        }

//        AsyncImage(
//            model = "https://example.com/image.jpg",
//            contentDescription = "Translated description of what the image contains"
//        )
    }
}

@Preview(device= Devices.PIXEL_2)
@Composable
fun PreviewVistaVacia(){
    EmptyView({})
}



