package com.example.generadorcurp.ui.form.ui

import com.example.generadorcurp.form.domain.*
import androidx.lifecycle.ViewModel
import com.example.generadorcurp.domain.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask


//private val savedStateHandle: SavedStateHandle
class FormViewModel(
    private val addNameUseCase: AddNameUseCase = AddNameUseCase(),
    private val addBirthUseCase: AddBirthUseCase = AddBirthUseCase(),
    private val addGenderCase: AddGenderUseCase = AddGenderUseCase(),
    private val addStateUseCase: AddStateUseCase = AddStateUseCase(),
    private val getCurpUseCase: GetCurpUseCase = GetCurpUseCase()

) : ViewModel() {

    private val _uiState =
        MutableStateFlow<CurpFormScreenState>(CurpFormScreenState.Init)
    val uiState: StateFlow<CurpFormScreenState> = _uiState

    private val _uiStateData = MutableStateFlow<CurpFormModelState>(CurpFormModelState())
    val uiStateData: StateFlow<CurpFormModelState> = _uiStateData

    fun initState() {
        println("CURP-FORM-STATE INIT")
        _uiState.value = CurpFormScreenState.Loading("cargando...estamos trabajando...")
        _uiStateData.value = CurpFormModelState(
            nombre = "Jaime",
            generoList = generos ,
            estadoList = estadosMexicanos,
        )

        Timer().schedule(timerTask {
            _uiState.value = CurpFormScreenState.Loaded
        }, 1000)
    }


    fun onEvent(event: CurpFormEvent) {
        when (event) {
            is CurpFormEvent.FechaChanged -> {
                _uiStateData.value = _uiStateData.value.copy(fechaNacimiento = event.fecha)
            }
            is CurpFormEvent.GeneroChanged -> {
                _uiStateData.value = _uiStateData.value.copy(genero = event.genero)
            }
            is CurpFormEvent.MaternoChanged -> {
                _uiStateData.value = _uiStateData.value.copy(materno = event.materno.uppercase())
            }
            is CurpFormEvent.PaternoChanged -> {
                _uiStateData.value =
                    _uiStateData.value.copy(paterno = event.paterno.uppercase())
            }
            is CurpFormEvent.NombreChanged -> {
                _uiStateData.value = _uiStateData.value.copy(nombre = event.nombre.uppercase())
            }
            is CurpFormEvent.EstadoChanged -> {
                _uiStateData.value = _uiStateData.value.copy(estado = event.estado)
            }
            is CurpFormEvent.Submit -> {
                submitData()
            }
            CurpFormEvent.Hide -> _uiState.value = CurpFormScreenState.Loaded
        }
    }


    private fun submitData() {
        _uiState.value = CurpFormScreenState.Loading("cargando...estamos trabajando...")
        val nameResult = addNameUseCase.invoque(_uiStateData.value.nombre)
        val middleNameResult = addNameUseCase.invoque(_uiStateData.value.paterno)
        val genderResult = addGenderCase.invoque(_uiStateData.value.genero.first)
        val stateResult = addStateUseCase.invoque(_uiStateData.value.estado.first)
        val birthResult = addBirthUseCase.invoque(_uiStateData.value.fechaNacimiento)

        if (nameResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(nombre = nameResult.error)
        }
        if (middleNameResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(paternoError = middleNameResult.error)
        }
        if (genderResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(generoError = genderResult.error)
        }
        if (birthResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(estadoError = birthResult.error)
        }
        if (stateResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(estadoError = stateResult.error)
        }

        if (nameResult is ResultCase.ResultValid &&
            middleNameResult is ResultCase.ResultValid &&
            genderResult is ResultCase.ResultValid &&
            birthResult is ResultCase.ResultValid &&
            stateResult is ResultCase.ResultValid
        ) {


            val result = getCurpUseCase.invoque(_uiStateData.value)
            if (result is ResultCase.ResultSuccess) {
                Timer().schedule(timerTask {
                    _uiState.value = CurpFormScreenState.Done(result.curp, _uiStateData.value.nombre)
                }, 1200)
            } else if (result is ResultCase.ResultError) {

            }

        }else{
            _uiState.value = CurpFormScreenState.Error(message = "Ocurrio un problema")
        }


    }
}


//class FormViewModel(
//    private val addNameUseCase: AddNameUseCase = AddNameUseCase(),
//    private val addBirthUseCase: AddBirthUseCase = AddBirthUseCase(),
//    private val addGenderUseCase: AddGenderUseCase = AddGenderUseCase(),
//    private val addStateUseCase: AddStateUseCase = AddStateUseCase(),
//    private val getCurpUseCase: GetCurpUseCase = GetCurpUseCase()
//): ViewModel() {
//
//    private val _uiEstadoData = MutableStateFlow<CurpModel>(CurpModel())
//    val uiEstadoData: StateFlow<CurpModel> = _uiEstadoData
//
//    private val _uiEstado = MutableStateFlow<FormUIestado>(FormUIestado.Init)
//    val uiEstado: StateFlow<FormUIestado> = _uiEstado
//
//    init {
//        initState()
//    }
//
//    fun initState(){
//        _uiEstado.value = FormUIestado.Loading("Cargando")
//        _uiEstadoData.value = CurpModel(
//            nombre = "JAIME EDUARDO",
//            primerApellido = "VERDUGO",
//            segundoApellido = "JIMENEZ",
//            fechaNacimiento = LocalDate.of(2001,11,23),
//            estado = "TS" to "Tamaulipas",
//            genero = "H"
//        )
//        Timer().schedule(timerTask { _uiEstado.value = FormUIestado.Loaded },2000)
//    }
//
//    fun onNameChange(nombre: String,primerApellido: String,segundoApellido: String){
//        if(nombre.matches(PATTERN_NAME)){
//            _uiEstadoData.value = _uiEstadoData.value.copy(nombre = nombre.uppercase())
//        }
//
//        if(primerApellido.matches(PATTERN_NAME)){
//            _uiEstadoData.value = _uiEstadoData.value.copy(primerApellido = primerApellido.uppercase())
//        }
//
//        if(segundoApellido.matches(PATTERN_NAME)){
//            _uiEstadoData.value = _uiEstadoData.value.copy(segundoApellido = segundoApellido.uppercase())
//        }
//
//        validarInputs()
//    }
//
//    fun onChangeFechaNacimiento(fechaNacimiento: LocalDate){
//        _uiEstadoData.value = _uiEstadoData.value.copy(fechaNacimiento = fechaNacimiento)
//        validarInputs()
//    }
//
//    fun onChangeGenero(genero : String){
//        _uiEstadoData.value = _uiEstadoData.value.copy(genero = genero)
//        validarInputs()
//    }
//
//    fun onChangeEstado(estado: Pair<String,String>){
//        _uiEstadoData.value = _uiEstadoData.value.copy(estado = estado)
//        validarInputs()
//    }
//
//    fun generarCURP(){
//        try {
//            _uiEstado.value = FormUIestado.Loading("Generando CURP")
//
//            var curp = ""
//            val inputData = _uiEstadoData.value
//            val nombre = normalizarNombre(inputData.nombre)
//            val primerApellido = normalizarNombre(inputData.primerApellido)
//            val segundoApellido = normalizarNombre(inputData.segundoApellido)
//
//            curp += primerApellido[0]
//            curp += getInternalVowel(primerApellido)
//            curp += if (segundoApellido.isEmpty()) "X" else segundoApellido[0]
//            curp += nombre[0]
//            curp += inputData.fechaNacimiento.year.toString().substring(2, 4)
//            curp += inputData.fechaNacimiento.monthValue.toString()
//            curp += inputData.fechaNacimiento.dayOfMonth.toString()
//            curp += inputData.genero
//            curp += inputData.estado.first
//            curp += getInternalConsonant(primerApellido)
//            curp += if (segundoApellido.isEmpty()) "X" else getInternalConsonant(segundoApellido)
//            curp += getInternalConsonant(nombre)
//            curp += if (inputData.fechaNacimiento.year < 2000) '0' else 'A'
//            curp += calcularUltimoDigitoCURP(curp)
//
//
//            _uiEstadoData.value = _uiEstadoData.value.copy(curp = curp)
//
//            Timer().schedule(timerTask {
//                _uiEstado.value =
//                    FormUIestado.Done(_uiEstadoData.value.curp, _uiEstadoData.value.nombre)
//            }, 2000)
//        }
//        catch (e: java.lang.Exception){
//            _uiEstado.value = FormUIestado.Error("ERROR")
//        }
//    }
//
//    fun validarInputs(){
//        var valid = true
//        val curpModel = uiEstadoData.value
//
//        when {
//            curpModel.nombre.isEmpty() -> valid = false
//            curpModel.primerApellido.isEmpty() -> valid = false
//            curpModel.segundoApellido.isEmpty() -> valid = false
//            curpModel.fechaNacimiento == LocalDate.now() -> valid = false
//            curpModel.genero.isEmpty() -> valid = false
//            curpModel.estado.first.isEmpty() -> valid = false
//        }
//
//        _uiEstadoData.value = _uiEstadoData.value.copy(btnEnabled = valid)
//    }
//
//}
//
//sealed class FormUIestado{
//    object Init: FormUIestado()
//    class Loading(val message: String) : FormUIestado()
//
//    object Loaded : FormUIestado()
//    class Done(val curp: String, val nombre: String) : FormUIestado()
//    class Error(val message: String) : FormUIestado()
//}
//
//data class CurpModel(
//    var btnEnabled: Boolean = false,
//    var curp: String = "",
//    var nombre: String = "",
//    var primerApellido: String = "",
//    var segundoApellido: String = "",
//    var fechaNacimiento: LocalDate = LocalDate.now(),
//    var genero: String = "",
//    var estado: Pair<String, String> = Pair("","")
//)