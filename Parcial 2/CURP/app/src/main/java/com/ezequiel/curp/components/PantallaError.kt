package com.ezequiel.curp.form.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ErrorView(
    error: String = "Lo sentimos, ocurrió un error. Intentalo más tarde.",
    onInitAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            Icons.Default.Close,
            modifier = Modifier.size(100.dp),
            contentDescription = "ERROR CURP",
            tint = Color.White
        )
        Text(
            text = error,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
        OutlinedButton(
            onClick = { onInitAction() }) {
            Text(text = "REINICIAR",color = Color.Red)

        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewErrorView() {
    ErrorView("Lo sentimos, ocurrió un error. \n         Intentalo más tarde.", {})
}