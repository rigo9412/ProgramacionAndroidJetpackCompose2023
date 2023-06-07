package com.almy.memorama


import android.content.Context
import android.media.MediaPlayer
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almy.gato.DataStoreManager
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.almy.gato.R
import com.almy.gato.ui.theme.rojoClaro
import com.almy.gato.ui.theme.verdeClaro
import kotlinx.coroutines.flow.first
import kotlin.random.Random



class SumaViewModel(private val context: Context,private val dataStoreManager: DataStoreManager): ViewModel(){
    private val _uiStateJuego= MutableStateFlow<Suma>(Suma())
    val uiStateJuego: StateFlow<Suma> = _uiStateJuego

    init {
        getData()
    }


    fun onSumaJugadorChanged(suma: String) {
        _uiStateJuego.value = _uiStateJuego.value.copy(sumaJugador = suma)
    }

    fun AsignarN(N: String) {
        _uiStateJuego.value = _uiStateJuego.value.copy(N = N.toInt())
    }

    fun verificarSumaCorrecta(){
        if(_uiStateJuego.value.sumaJugador.toInt() == _uiStateJuego.value.Suma){
            _uiStateJuego.value = _uiStateJuego.value.copy(esCorrectaSuma = true)
        }
        else{
            _uiStateJuego.value = _uiStateJuego.value.copy(esCorrectaSuma = false)
        }
    }
     fun IniciarSuma(){
         viewModelScope.launch {
             var numeroAleatorio: Int = 0
             var N =  _uiStateJuego.value.N
             _uiStateJuego.value = _uiStateJuego.value.copy(MostrarBoton = false)
             _uiStateJuego.value = _uiStateJuego.value.copy(MostrarInputN = false)

             _uiStateJuego.value = _uiStateJuego.value.copy(N = N)

             for (i in 0..N) {

                 if (i == N){
                     _uiStateJuego.value = _uiStateJuego.value.copy(MostrarSuma = true)
                     _uiStateJuego.value = _uiStateJuego.value.copy(MostrarBoton = true)
                     _uiStateJuego.value = _uiStateJuego.value.copy(MostrarInput = true)


                     delay(5000)
                     verificarSumaCorrecta()

                     if(_uiStateJuego.value.esCorrectaSuma){
                         _uiStateJuego.value = _uiStateJuego.value.copy(colorPantalla = verdeClaro)
                         delay(2000)
                         Reiniciar()

                     }
                     else{
                         _uiStateJuego.value = _uiStateJuego.value.copy(colorPantalla = rojoClaro)
                         delay(2000)
                         Reiniciar()
                     }
                     _uiStateJuego.value = _uiStateJuego.value.copy(MostrarInputN = true)
                     break
                 }

                 do
                 {
                     numeroAleatorio = Random.nextInt(-9, 9)
                 }
                 while (numeroAleatorio == 0)

                 val MediaPlayer = MediaPlayer.create(context,R.raw.pig)
                 MediaPlayer.start()
                 _uiStateJuego.value = _uiStateJuego.value.copy(NumeroAleatorio = numeroAleatorio)
                 _uiStateJuego.value = _uiStateJuego.value.copy(Suma = _uiStateJuego.value.Suma + numeroAleatorio)
                 delay(500)

             }
         }
     }

    fun Reiniciar(){
        _uiStateJuego.value = _uiStateJuego.value.copy(Reinicio = true)

        _uiStateJuego.value = _uiStateJuego.value.copy(
            Suma = 0,
             NumeroAleatorio = 0,
             MostrarSuma= false,
             MostrarBoton = true,
            N = 0,
            Reinicio= false,
            sumaJugador = "0",
             esCorrectaSuma = false,
            MostrarInput = false,
            colorPantalla = Color.Gray,
            MostrarInputN = true

        )

    }


    fun getData()
    {
        viewModelScope.launch {
            _uiStateJuego.value = _uiStateJuego.value.copy(
                Suma = 0,
                NumeroAleatorio = 0,
                MostrarSuma= false,
                MostrarBoton = true,
                N = dataStoreManager.getFromDataStore().first().N,
                Reinicio= false,
                sumaJugador = "0",
                esCorrectaSuma = false,
                MostrarInput = false,
                colorPantalla = Color.Gray,
                MostrarInputN = true

            )
        }
    }
}

data class Suma(
    var NumeroAleatorio: Int = 0,

    var Suma: Int = 0,

    var N: Int = 0,

    var MostrarSuma: Boolean = false,

    var MostrarBoton: Boolean = true,

    var Reinicio: Boolean = false,

    var sumaJugador: String = "0",

    var esCorrectaSuma: Boolean = false,

    var MostrarInput: Boolean = false,

    var colorPantalla: Color = Color.Gray,

    var MostrarInputN: Boolean = true

)




