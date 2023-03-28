package com.rfcpractica.curp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
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
             LinearProgressIndicator(
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(15.dp),
                 backgroundColor = Color.LightGray,
                 color = Color.Green //progress color
             )
         }
         Text(text = message)
     }
}

@Preview(device = Devices.NEXUS_10)
@Composable
fun PartialViewLoadingView() {
    LoadingView()
}