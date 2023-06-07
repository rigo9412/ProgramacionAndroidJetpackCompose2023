package com.almy.gato

import android.content.Context
import android.media.MediaPlayer


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random
import com.almy.gato.R


class SumaViewModel(private val context: Context): ViewModel(){
    private val _uiStateSuma = MutableStateFlow<Suma>(Suma())
    val uiStateSuma: StateFlow<Suma> = _uiStateSuma


    fun IniciarJuego(){

        viewModelScope.launch {
            var numeroAleatorio: Int = 0
            var rango = Random.nextInt(1, 100)
            _uiStateSuma.value = _uiStateSuma.value.copy(esconderBoton = false)//rango de numeros del 1 al 100

            for (i in 0..rango) { // para generar los numeros aleatorios que se van a sumar
                if (i == rango){ // si i es igual al rango (n) quiere decir que ya se llego al final

                    _uiStateSuma.value = _uiStateSuma.value.copy(SumaFin = true)
                    _uiStateSuma.value = _uiStateSuma.value.copy(esconderBoton = true)// se muestra la suma de los numeroshb
                    delay(2000)
                    Reiniciar()
                    break;
                }

                do
                {
                    numeroAleatorio = Random.nextInt(-9, 9)
                }
                while (numeroAleatorio == 0)
                val mediaPlayer = MediaPlayer.create(context, R.raw.jump)
                mediaPlayer.start()
                _uiStateSuma.value = _uiStateSuma.value.copy(numero = numeroAleatorio)
                _uiStateSuma.value = _uiStateSuma.value.copy(Suma = _uiStateSuma.value.Suma + numeroAleatorio)
                delay(500)
               // R.raw.jump
            }
        }
    }
    fun Reiniciar(){
        _uiStateSuma.value = _uiStateSuma.value.copy(
            Suma = 0,
        numero = 0,
            SumaFin = false,
            esconderBoton = true
        )

    }

}

data class Suma(

    var numero: Int = 0, // se crea la variable para llevar el control de los numeros aleatorios
    var Suma: Int = 0, // se crea una variable para la suma
    var SumaFin:Boolean = false, //sirve para saber si ya llegamos al final de la suma total de los numeros
    var esconderBoton: Boolean = true
)

