package com.almy.gato

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.almy.gato.components.*
import com.almy.gato.ui.theme.*

@Composable
fun NumeroScreen(viewModel: SumaViewModel) {
    var state = viewModel.uiStateSuma.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Column(modifier = Modifier
            .fillMaxSize().align(Alignment.CenterHorizontally)
            ) {

            CustomText(text =  state.numero.toString(),Modifier.padding(5.dp).align(Alignment.CenterHorizontally))


            if (state.SumaFin) {
                CustomText(text = "Suma de numeros: " + state.Suma,Modifier.padding(5.dp).align(Alignment.CenterHorizontally))
            }
            Spacer(modifier = Modifier.height(10.dp))

            if(state.esconderBoton)
            {
                Iniciar(
                    text = "INICIAR SUMA",
                    Modifier
                        .size(200.dp, 60.dp)
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    viewModel.IniciarJuego()
                }

            }


        }



    }
}