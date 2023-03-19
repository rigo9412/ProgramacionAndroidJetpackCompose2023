package com.example.registrocurp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Text(
            text = "CURP GENERADO CON EXITO",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color(46,139,87 )
        )
        OutlinedButton(
            onClick = { action() }) {
            Text(text = "VER CURP",color = Color.Black)
        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewEmptyView() {
    EmptyView( {})
}