package com.example.generadorcurp.form.ui.components

import com.example.generadorcurp.form.domain.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class FormViewModel(): ViewModel() {

    private val _uiEstadoData = MutableStateFlow<CurpModel>(CurpModel())
    val uiEstadoData: StateFlow<CurpModel> = _uiEstadoData

    private val _uiEstado = MutableStateFlow<FormUIestado>(FormUIestado.Init)
    val uiEstado: StateFlow<FormUIestado> = _uiEstado

    fun initState(){
        _uiEstado.value = FormUIestado.Loading("Cargando")
        _uiEstadoData.value = CurpModel(
            nombre = "JAIME EDUARDO",
            primerApellido = "VERDUGO",
            segundoApellido = "JIMENEZ",
            fechaNacimiento = LocalDate.of(2001,11,23),
            estado = "TS" to "Tamaulipas",
            genero = "H" to "Hombre",
            sexList = getGeneros(),
            statesList = getEstados()
        )
        Timer().schedule(timerTask { _uiEstado.value = FormUIestado.Loaded },2000)
    }

    fun onNameChange(nombre: String,primerApellido: String,segundoApellido: String){
        if(nombre.matches(PATTERN_NAME)){
            _uiEstadoData.value = _uiEstadoData.value.copy(nombre = nombre.uppercase())
        }

        if(primerApellido.matches(PATTERN_NAME)){
            _uiEstadoData.value = _uiEstadoData.value.copy(primerApellido = primerApellido.uppercase())
        }

        if(segundoApellido.matches(PATTERN_NAME)){
            _uiEstadoData.value = _uiEstadoData.value.copy(segundoApellido = segundoApellido.uppercase())
        }
    }

    fun onChangeFechaNacimiento(fechaNacimiento: LocalDate){
        
    }

    fun onChangeGenero(genero : Pair<String, String>){
        _uiEstadoData.value = _uiEstadoData.value.copy(genero = genero)
    }

}

sealed class FormUIestado{
    object Init: FormUIestado()
    class Loading(val message: String) :FormUIestado()

    object Loaded : FormUIestado()
    class Done(val curp: String, val nombre: String) : FormUIestado()
    class Error(val message: String) : FormUIestado()
}

data class CurpModel(
    var curp: String = "",
    var nombre: String = "",
    var primerApellido: String = "",
    var segundoApellido: String = "",
    var fechaNacimiento: LocalDate = LocalDate.now(),
    var genero: Pair<String,String> = Pair("",""),
    var estado: Pair<String,String> = Pair("",""),
    val sexList: ArrayList<Pair<String, String>> = ArrayList(),
    val statesList: ArrayList<Pair<String, String>> = ArrayList()
)