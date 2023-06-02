package com.example.notificationdemo.cronometro

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notificationdemo.NotificationViewModel
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer

class Cronometro {
    private var tiempoInicial: Long = 0
    private var tiempoPausado: Long = 0
    var isRunning: MutableState<Boolean> = mutableStateOf(false)
    var isPaused: MutableState<Boolean> = mutableStateOf(false)
    private var timerTask: Timer? = null
    private var timerTask2: Timer? = null
    var tiempoTranscurrido: MutableState<Long> = mutableStateOf(0)


    fun iniciarCronometro(
        viewModel: NotificationViewModel
    ) {
        if (!isRunning.value) {
            viewModel.showSimpleNotification()
            tiempoInicial = System.currentTimeMillis()
            timerTask = timer(period = 10) {
                tiempoTranscurrido.value = System.currentTimeMillis() - tiempoInicial + tiempoPausado
            }
            timerTask2 = timer(period = 1000) {
                viewModel.updateSimpleNotification(formatoTiempoNotificacion())
            }
            isRunning.value = true
            isPaused.value = false
        }
    }

    fun pausarCronometro() {
        if (isRunning.value) {
            timerTask?.cancel()
            timerTask2?.cancel()
            tiempoPausado += System.currentTimeMillis() - tiempoInicial
            isRunning.value = false
            isPaused.value = true
        }
    }

    fun reiniciarCronometro(viewModel: NotificationViewModel) {
        tiempoInicial = 0
        tiempoPausado = 0
        isRunning.value = false
        isPaused.value = false
        tiempoTranscurrido.value = 0
        timerTask?.cancel()
        timerTask2?.cancel()
        viewModel.cancelSimpleNotification()
    }

    fun formatoTiempo(): String {
        val minutos = (tiempoTranscurrido.value / 1000 / 60) % 60
        val segundos = (tiempoTranscurrido.value / 1000) % 60
        val milisegundos = (tiempoTranscurrido.value % 1000) / 10
        return String.format("%02d:%02d:%02d", minutos, segundos, milisegundos)
    }

    fun formatoTiempoNotificacion(): String {
        val minutos = (tiempoTranscurrido.value / 1000 / 60) % 60
        val segundos = (tiempoTranscurrido.value / 1000) % 60
        return String.format("%02d:%02d", minutos, segundos)
    }
}

