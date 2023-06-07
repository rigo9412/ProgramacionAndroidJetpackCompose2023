package com.example.nivelacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun PantallaInput(
    viewModel: JuegoViewModel
) {
    val data = viewModel.StateData.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = data.resultadoIngresado,
            onValueChange = { viewModel.Prueba(it) },
             label = { Text("Ingrese un número") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth().background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.CompararValor()},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Comprobar resultado")
        }
    }
}


@Composable
fun PantallaCorrecto(
    viewModel: JuegoViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "¡Felicidades!",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
    fun PantallaIncorrecto(
    viewModel: JuegoViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(). background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Equivocado!",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = data.suma.toString(),
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
    }

}


@Composable
fun ConsultarScreen(
    viewModel: JuegoViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(). background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        data.listaMostrar.forEach(){
            Text(text = it.toString())
        }
    }

}



