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

    init {
        initState()
    }

    fun initState(){
        _uiEstado.value = FormUIestado.Loading("Cargando")
        _uiEstadoData.value = CurpModel(
            nombre = "JAIME EDUARDO",
            primerApellido = "VERDUGO",
            segundoApellido = "JIMENEZ",
            fechaNacimiento = LocalDate.of(2001,11,23),
            estado = "TS" to "Tamaulipas",
            genero = "H"
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

        validarInputs()
    }

    fun onChangeFechaNacimiento(fechaNacimiento: LocalDate){
        _uiEstadoData.value = _uiEstadoData.value.copy(fechaNacimiento = fechaNacimiento)
        validarInputs()
    }

    fun onChangeGenero(genero : String){
        _uiEstadoData.value = _uiEstadoData.value.copy(genero = genero)
        validarInputs()
    }

    fun onChangeEstado(estado: Pair<String,String>){
        _uiEstadoData.value = _uiEstadoData.value.copy(estado = estado)
        validarInputs()
    }

    fun generarCURP(){
        _uiEstado.value = FormUIestado.Loading("Generando CURP")

        var curp = ""
        val inputData = _uiEstadoData.value
        val nombre = normalizarNombre(inputData.nombre)
        val primerApellido = normalizarNombre(inputData.primerApellido)
        val segundoApellido = normalizarNombre(inputData.segundoApellido)

        curp += primerApellido[0]
        curp += getInternalVowel(primerApellido)
        curp += if(segundoApellido.isEmpty()) "X"  else segundoApellido[0]
        curp += nombre[0]
        curp += inputData.fechaNacimiento.year.toString().substring(2,4)
        curp += inputData.fechaNacimiento.monthValue.toString()
        curp += inputData.fechaNacimiento.dayOfMonth.toString()
        curp += inputData.genero
        curp += inputData.estado.first
        curp += getInternalConsonant(primerApellido)
        curp += if(segundoApellido.isEmpty()) "X"  else getInternalConsonant(segundoApellido)
        curp += getInternalConsonant(nombre)
        curp += if(inputData.fechaNacimiento.year < 2000) '0' else 'A'
        curp += calcularUltimoDigitoCURP(curp)


        _uiEstadoData.value = _uiEstadoData.value.copy(curp = curp)

        Timer().schedule(timerTask { _uiEstado.value = FormUIestado.Done(_uiEstadoData.value.curp, _uiEstadoData.value.nombre) },2000)

    }

    fun validarInputs(){
        var valid = true
        val curpModel = uiEstadoData.value

        when {
            curpModel.nombre.isEmpty() -> valid = false
            curpModel.primerApellido.isEmpty() -> valid = false
            curpModel.segundoApellido.isEmpty() -> valid = false
            curpModel.fechaNacimiento == LocalDate.now() -> valid = false
            curpModel.genero.isEmpty() -> valid = false
            curpModel.estado.first.isEmpty() -> valid = false
        }

        _uiEstadoData.value = _uiEstadoData.value.copy(btnEnabled = valid)
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
    var btnEnabled: Boolean = false,
    var curp: String = "",
    var nombre: String = "",
    var primerApellido: String = "",
    var segundoApellido: String = "",
    var fechaNacimiento: LocalDate = LocalDate.now(),
    var genero: String = "",
    var estado: Pair<String, String> = Pair("","")
)