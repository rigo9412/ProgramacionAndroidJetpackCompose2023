package com.almy.gato

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.almy.gato.components.*
import com.almy.gato.ui.theme.*
import com.almy.memorama.SumaViewModel



@SuppressLint("UnrememberedMutableState")
@Composable
fun JuegoSumaScreen(viewModel: SumaViewModel) {

    val state = viewModel.uiStateJuego.collectAsState().value
    val numero = state.NumeroAleatorio.toString()
    var textValue  by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(state.colorPantalla)
    ) {
        Column(
            modifier = Modifier
                .size(500.dp, 50.dp)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally),
        ) {
            TitleText(text = "SUMA!", Modifier.align(Alignment.CenterHorizontally))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(Modifier.align(Alignment.CenterHorizontally)) {

            if (!state.MostrarSuma) {
                CustomText(
                    text = numero,
                    Modifier.padding(5.dp).align(Alignment.CenterHorizontally)
                )
            }

            if(state.MostrarInputN){
                TextField(
                    value = textValue,
                    onValueChange = {textValue = it},
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                    .width(250.dp)
                    .height(60.dp)
                    .testTag("lblTest"),
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = Color.Black,
                    )
                )

            }

            if (state.MostrarSuma) {
                CustomText(
                    text = "SUMA: " + state.Suma,
                    Modifier.padding(5.dp).align(Alignment.CenterHorizontally)
                )
            }

            if (state.MostrarInput){
                CustomInput(
                    state.sumaJugador,
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(250.dp)
                        .height(60.dp)
                        .testTag("lblTest")
                ) {
                    viewModel.onSumaJugadorChanged(it)
                }

            }
            Spacer(modifier = Modifier.height(10.dp))

            if (state.MostrarBoton) {
                IniciarButton(
                    text = "INICIAR SUMA",
                    Modifier
                        .size(200.dp, 60.dp)
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    viewModel.AsignarN(textValue.text)
                    viewModel.IniciarSuma()
                }
            }
        }
    }
}



