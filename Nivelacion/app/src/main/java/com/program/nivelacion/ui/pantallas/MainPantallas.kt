package com.program.nivelacion.ui.pantallas

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.program.nivelacion.ui.data.MainViewModel
import com.program.nivelacion.ui.data.UiState

@Composable
fun ControlDePantallas(viewModel: MainViewModel,mediaPlayer: MediaPlayer){
    val state = viewModel.uiState.collectAsState().value

    when(state){
        is UiState.pantallaJuego -> PantallaJuego(viewModel,mediaPlayer)
        is UiState.pantallaResultado -> PantallaResultado(viewModel)
        is UiState.pantallaMostrarLista -> pantallaMostrasrLista(viewModel)
        else->{ }
    }
}
@Composable
fun pantallaMostrasrLista(viewModel: MainViewModel){
    val data = viewModel.uiStateData.collectAsState().value
    
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        data.LISTARESULTADOS.forEach(){
            Text(text = it.resultado1.toString(), fontSize = 16.sp)
        }

    }
}

@Composable
fun PantallaResultado(viewModel: MainViewModel){
    val data = viewModel.uiStateData.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(data.ColorFondo)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Row() {
            Text(text = "¿Cual es el resultado?", fontSize = 30.sp)
        }
            Spacer(modifier = Modifier.padding(10.dp))
        Row() {
            OutlinedTextField(
                value = data.CadenaResultado,
                onValueChange = {
                    viewModel.TextField(it)
                },
                label = { Text("Resultado") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Row() {
            Button(onClick = {viewModel.ConfirmarResultado()
            }){
                Text(text = "Comfirmar resultado")
            }
        }

        Row() {
            Text(text = data.Mensaje, fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row() {
            Text(text = "Resultado" +data.Resultado.toString(), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(){
            Button(onClick = {viewModel.GuardarValorBaseDatos()
            }){
                Text(text = "Guardar Base de datos")
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Row(){
            Button(onClick = {viewModel.MonstrarResultados()
            }){
                Text(text = "Mostrar resultados")
            }
        }
    }
}

@Composable
fun PantallaJuego(viewModel: MainViewModel,mediaPlayer: MediaPlayer){

    val data = viewModel.uiStateData.collectAsState().value


    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center){

        Row(){

            Text(text = data.Numero0_9.toString(), fontSize = 80.sp)
        }
        Row(){
           Button(onClick = {viewModel.N_vecesAleatorio(mediaPlayer)
           }){
               Text(text = "Iniciar Juego")
           }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Row(){
            Text(text = "Ingrese el valor de Nveces", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))

        Row(){
            OutlinedTextField(
                value = data.Nveces,
                onValueChange = {
                    viewModel.MostrarTXT(it)
                    //viewModel.GuardarNveces()
                },
                label = { Text("Resultado") },
                modifier = Modifier.fillMaxWidth()
            )
        }

    }



}