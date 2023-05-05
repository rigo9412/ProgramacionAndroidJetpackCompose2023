package com.almy.simondice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.almy.simondice.components.*


@Composable
fun GatoScreen(
    viewModel: SimonDiceViewModel
){
    val state = viewModel.uiStateSimonDice.collectAsState().value

    Column(        
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            for (i in 1..2){
                state.botones[i]?.let { botonNumero ->
                    state.botonesColores[i]?.let{ botonColor  ->
                        state.botonesHabilitados[i]?.let{ botonHabilitado ->
                            CustomButton(
                                text = botonNumero,
                                color = botonColor,
                                enabled = botonHabilitado
                            ) {
                                    viewModel.concatenarPatronJugador(i.toString())
                            }

                        }

                    }
                }
            }
        }

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            for (i in 3..4){
                state.botones[i]?.let { botonNumero ->
                    state.botonesColores[i]?.let{ botonColor  ->
                        state.botonesHabilitados[i]?.let{ botonHabilitado ->
                            CustomButton(
                                text = botonNumero,
                                color = botonColor,
                                enabled = botonHabilitado
                            ) {
                                viewModel.concatenarPatronJugador(i.toString())
                            }

                        }

                    }
                }
            }
        }

        InitButton(text = "Iniciar", modifier = Modifier.align(Alignment.CenterHorizontally)) {
            val simonPatron = state.simonPatron
            simonPatron.forEachIndexed{index,char ->
                viewModel.iniciarPatronSimon(char)
            }
            viewModel.habilitarBotones()

        }
    }
}