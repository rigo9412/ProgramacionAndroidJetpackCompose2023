package com.example.nivelacion

import android.app.Application
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.random.Random

class JuegoViewModel(private val dataStoreManager: DataStoreManager, application: Application): ViewModel() {
    private val _juegoState = MutableStateFlow<juegoState>(juegoState())
    val uiStateData: StateFlow<juegoState> = _juegoState
        //conectar base de datos
    val infoDB = InformacionRoomDatabase.getInstance(application)
    val infoDao = infoDB.infoDao()
    private val informacion = MutableStateFlow<Informacion>(Informacion())
    val infoCon: StateFlow<Informacion> = informacion

    //variable que indica el estado de la interfaz
    private val _uiState = MutableStateFlow<vistas>(vistas.Init)
    val uiState: StateFlow<vistas> = _uiState

    private val _resultado= MutableStateFlow<Resultados>(Resultados())
    val StateData: StateFlow<Resultados> = _resultado

    init {
        _uiState.value = vistas.Juego
        viewModelScope.launch {
            _juegoState.value = _juegoState.value.copy(LimiteN = dataStoreManager.getFromDataStore().first())
            GetInfo()
        }
    }

    fun Tiempo(mediaPlayer: MediaPlayer) {
        var sumaNumeros =0
        if(_juegoState.value.LimiteN.toInt()>0){
            var contador:Int=0
            var cantidadACambiar:Int = _juegoState.value.LimiteN.toInt()
            viewModelScope.launch {
                while(_juegoState.value.contador < 4 ){
                    delay(500)
                    _juegoState.value = _juegoState.value.copy(
                        contador = contador++
                    )
                }
                val randomRange = Random.nextInt(1, 100)

                var  listaNumeros = _juegoState.value.list
                listaNumeros.clear()
                for(i in 1..cantidadACambiar){
                    delay(500)
                    _juegoState.value = _juegoState.value.copy(
                        iniciar=true,
                        numero = Random.nextInt(-9, 9),

                        )
                    mediaPlayer.start()

                    listaNumeros.add(_juegoState.value.numero)
                    sumaNumeros = _juegoState.value.numero + sumaNumeros
                }

                _juegoState.value = _juegoState.value.copy(
                    iniciar = false,
                    suma = sumaNumeros,
                    list = listaNumeros,
                )

            }
        }else
        {var contador:Int=0
            viewModelScope.launch {
                while(_juegoState.value.contador < 4 ){
                    delay(500)
                    _juegoState.value = _juegoState.value.copy(
                        contador = contador++
                    )
                }
                val randomRange = Random.nextInt(1, 100)
                var sumaNumeros =0
                var  listaNumeros = _juegoState.value.list
                listaNumeros.clear()
                for(i in 0..randomRange){
                    delay(500)
                    _juegoState.value = _juegoState.value.copy(
                        iniciar=true,
                        numero = Random.nextInt(-9, 9),

                        )
                    mediaPlayer.start()

                    listaNumeros.add(_juegoState.value.numero)
                    sumaNumeros = _juegoState.value.numero + sumaNumeros
                }

                _juegoState.value = _juegoState.value.copy(
                    iniciar = false,
                    suma = sumaNumeros,
                    list = listaNumeros,
                )

            }}

            infoCon.value.info1 = sumaNumeros;

    }

    fun CambiarPantalla(){
        _uiState.value = vistas.Resultados
    }

    fun Prueba(valor: String){
        _resultado.value = _resultado.value.copy(
            resultadoIngresado = valor
        )

    }
    fun CompararValor(){
      if(  _juegoState.value.suma  == _resultado.value.resultadoIngresado.toInt()){
          _uiState.value = vistas.ResultadoCorrecto
      }
        else{
          _uiState.value = vistas.ResultadoIncorrecto
        }
    }

    fun CambiarPreferencias(valorString: String){
        _juegoState.value = _juegoState.value.copy(
           LimiteN = valorString
        )
    }

    fun GuardarDataStore(){
        viewModelScope.launch {
            dataStoreManager.saveToDataStore(_juegoState.value.LimiteN)
        }
    }

    fun AddInfo(){
        viewModelScope.launch {
            infoDao.addResult(infoCon.value)
        }
    }

    fun GetInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            _juegoState.value.listaMostrar=infoDao.getAllResults().toMutableList()
        }
    }

    fun ConsultarInfo(){
        _uiState.value = vistas.MostrarResultado
    }
}


