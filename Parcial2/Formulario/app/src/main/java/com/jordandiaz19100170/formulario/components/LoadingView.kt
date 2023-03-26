package com.jordandiaz19100170.formulario.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingView(
    message: String = "Cargando"
) {
     Column(
         modifier = Modifier.fillMaxSize().background(Color.White),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally,
     ) {

         CircularProgressIndicator(
             modifier  = Modifier.size(52.dp)
         )
         Text(text = message)
     }
}

@Preview(device = Devices.NEXUS_10)
@Composable
fun PartialViewLoadingView() {
    LoadingView()
}