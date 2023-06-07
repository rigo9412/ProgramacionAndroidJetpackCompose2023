package com.example.nivelacion

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.nivelacion.JuegoViewModel



@Composable
fun ControlVM(
    viewModel: JuegoViewModel,
    mediaPlayer: MediaPlayer
){
    val state = viewModel.uiState.collectAsState().value

    when(state)
    {
        is vistas.Juego-> JuegoScreen(viewModel,mediaPlayer)
        is vistas.Resultados  -> PantallaInput(viewModel)
        is vistas.ResultadoCorrecto -> PantallaCorrecto(viewModel)
        is vistas. ResultadoIncorrecto -> PantallaIncorrecto(viewModel)
        is vistas.MostrarResultado -> ConsultarScreen(viewModel)
        else -> { }
    }
}


private lateinit var mediaPlayer: MediaPlayer
@Composable
fun JuegoScreen(
    viewModel: JuegoViewModel,
    mediaPlayer: MediaPlayer
){
    val data = viewModel.uiStateData.collectAsState().value

    fun playSound() {
        mediaPlayer.start()
    }
        Column(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(Color.White), verticalArrangement = Arrangement.Center) {
            Row( modifier = Modifier.size(300.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                if(data.contador<4){
                    Box( modifier =  Modifier) {
                        Text(text = data.contador.toString(), color = Color.Magenta, fontSize = 34.sp, fontWeight = FontWeight.Bold)
                    }
                }
                else{
                    Box( modifier =  Modifier) {
                        Text(text = data.numero.toString(), color = Color.Black, fontSize = 24.sp)
                    }
                }

            }
            Button(
                onClick = { viewModel.Tiempo(mediaPlayer) }
//                        onClick = { viewModel.GuardarDataStore()}
                ) {
                Text(text = "Iniciar")
                
            }
            Row(modifier = Modifier.size(100.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                TextField(value = data.LimiteN, onValueChange = {viewModel.CambiarPreferencias(it)}, modifier = Modifier.background(Color.Gray) )
            }
            data.list.forEach(){
                if(it<0)
                {
                    Text(text = it.toString(), color = Color.Red, fontSize = 20.sp)
                }
                else{
                    Text(text = it.toString(), color = Color.Blue, fontSize = 20.sp)
                }

            }
            Row(modifier = Modifier.size(100.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                if (!data.iniciar){

                    //muestra el resultado de la suma
                    Box( modifier =  Modifier) {
                        Text(text = data.suma.toString(), color = Color.Black, fontSize = 24.sp)

                    }
                    Button(onClick = { viewModel.CambiarPantalla() }) {
                        Text(text = "Ingresar Resultado")
                    }


                }
            }

            if (data.iniciar == false){
                Button(
                    onClick = { viewModel.Tiempo(mediaPlayer) }
                ) {
                    Text(text = "Reiniciar")

                }
                Button(onClick = { viewModel.AddInfo() }) {
                    Text(text = "Ingresar info a la bd")
                }
                Button(onClick = { viewModel.ConsultarInfo() }) {
                    Text(text = "Mostrar informacion bd")
                }

            }



    }

}

