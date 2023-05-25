package com.practica.notificaciones.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.practica.notificaciones.NotificationViewModel
import com.practica.notificaciones.cronometro

@Composable
fun MainScreen(
    //cronometro: Cronometro,
    notificationViewModel: NotificationViewModel = hiltViewModel()
)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = cronometro.formatoTiempo(), fontSize = 60.sp)
        Spacer(modifier = Modifier.padding(bottom = 35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                modifier = Modifier
                    .width(110.dp)
                    .height(40.dp),
                onClick = { if(cronometro.isPaused.value) cronometro.reiniciarCronometro(notificationViewModel)
                else cronometro.pausarCronometro() },
                enabled = if(cronometro.isRunning.value) true
                else cronometro.isPaused.value
            ) {
                Text(text = if(cronometro.isPaused.value) "Reiniciar" else "Pausar")
            }
            Button(
                modifier = Modifier
                    .width(110.dp)
                    .height(40.dp),
                onClick = { cronometro.iniciarCronometro(notificationViewModel)},
                enabled = !cronometro.isRunning.value
            ) {
                Text(text = if(cronometro.isPaused.value) "Reanudar" else "Iniciar")
            }
        }
    }
}