package com.program.nivelacion.ui.data


import android.app.Application
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.program.nivelacion.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MainViewModel (private val dataStoreManager: DataStoreManager,application: Application): ViewModel(){

    private val _uiStateData = MutableStateFlow<UiStateData>(UiStateData())
    val uiStateData: StateFlow<UiStateData> = _uiStateData

    private val _uiState = MutableStateFlow<UiState>(UiState.Init)
    val uiState : StateFlow<UiState> = _uiState

    private val _currentResultado = MutableStateFlow<Resultados>(Resultados())
    val currentResultado: StateFlow<Resultados> = _currentResultado

    val ResultadoDB = ResultadoRoomDatabase.getInstance(application)
    val resDao = ResultadoDB.resultadoDao()

    init{
        _uiState.value = UiState.pantallaJuego
        viewModelScope.launch {
            _uiStateData.value = _uiStateData.value.copy(Nveces = dataStoreManager.consultarDataStore().first().Nveces)
        }

    }

    fun TextField(valor: String){
        _uiStateData.value = _uiStateData.value.copy(CadenaResultado = valor)
    }

    //
    fun GuardarNveces(){
        viewModelScope.launch {

            dataStoreManager.guardarDataStore(_uiStateData.value)
        }
    }

    //
    fun MostrarTXT(valor : String){
        _uiStateData.value = _uiStateData.value.copy(Nveces = valor)
    }

    fun ConfirmarResultado(){
        if(_uiStateData.value.Resultado.toString() == _uiStateData.value.CadenaResultado){
            _uiStateData.value = uiStateData.value.copy(ColorFondo = Color.GREEN
            , Mensaje = "Correcto")
        }else{
            _uiStateData.value = uiStateData.value.copy(ColorFondo = Color.RED
                , Mensaje = "Error")
        }
    }

    fun GuardarValorBaseDatos(){

        viewModelScope.launch {
            _currentResultado.value.resultado1 = _uiStateData.value.CadenaResultado.toInt()
            resDao.addResult(_currentResultado.value)
        }
    }

    fun MonstrarResultados(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiStateData.value = uiStateData.value.copy(LISTARESULTADOS = resDao.getAllResults().toMutableList())
            _uiState.value = UiState.pantallaMostrarLista
        }

    }

    fun N_vecesAleatorio(mediaPlayer: MediaPlayer){

        viewModelScope.launch {
        var a = Random.nextInt(0,100)
        //_uiStateData.value = _uiStateData.value.copy(Nveces = a.toString())
        GuardarNveces()
        for(  i in 0.._uiStateData.value.Nveces.toInt()){
            mediaPlayer.start()

            delay(500)

                var aleatorio  = Random.nextInt(-9,9)
                if(aleatorio != 0){
                    _uiStateData.value = _uiStateData.value.copy(Numero0_9 = aleatorio)
                    _uiStateData.value = uiStateData.value.copy(Resultado =  _uiStateData.value.Resultado + aleatorio )
                }
            mediaPlayer.stop()
            }
            _uiState.value = UiState.pantallaResultado
        }
    }

    fun Aleatorio_0_9(){

    }
}