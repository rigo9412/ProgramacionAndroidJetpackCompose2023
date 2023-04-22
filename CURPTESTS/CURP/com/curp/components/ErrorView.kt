package com.curp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentSatisfiedAlt
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
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
    error: String = "Error",
    onInitAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            Icons.Default.SentimentSatisfiedAlt,
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
            Text(text = "REINICIAR",color = Color.Blue)

        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewErrorView() {
    ErrorView("error", {})
}