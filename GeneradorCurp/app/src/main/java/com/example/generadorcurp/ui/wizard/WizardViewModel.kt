package com.example.generadorcurp.ui.wizard

import androidx.lifecycle.ViewModel
import com.example.generadorcurp.domain.*
import com.example.generadorcurp.form.domain.generos
import com.example.generadorcurp.ui.curp.ui.estadosMexicanos
import com.example.generadorcurp.ui.form.ui.CurpFormModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WizardViewModel(
    private val addNameUseCase: AddNameUseCase = AddNameUseCase(),
    private val addBirthUseCase: AddBirthUseCase = AddBirthUseCase(),
    private val addGenderUseCase: AddGenderUseCase = AddGenderUseCase(),
    private val addStateUseCase: AddStateUseCase = AddStateUseCase(),
    private val getCurpUseCase: GetCurpUseCase = GetCurpUseCase()
) : ViewModel(){
    private val _uiWizardState = MutableStateFlow<WizardScreenState>(WizardScreenState.StepNone)
    val uiWizardState: StateFlow<WizardScreenState> = _uiWizardState

    private val _uiStateData = MutableStateFlow<CurpFormModelState>(CurpFormModelState())
    val uiStateData: StateFlow<CurpFormModelState> = _uiStateData

    fun initState() {
        println("CURP-WIZARD-STATE INIT")
        _uiStateData.value = CurpFormModelState(
            nombre = "test",
            generoList = generos,
            estadoList = estadosMexicanos
        )
    }

    fun onEvent(event: WizardScreenEvent){
        when(event){
            is WizardScreenEvent.Back -> back(event.origin,event.destination)
            is WizardScreenEvent.FechaChanged -> {_uiStateData.value = _uiStateData.value.copy(fechaNacimiento = event.fecha, fechaNacimientoError = null)}
            is WizardScreenEvent.GeneroChanged -> {_uiStateData.value = _uiStateData.value.copy(genero = event.genero, generoError = null)}
            is WizardScreenEvent.MaternoChanged -> {_uiStateData.value = _uiStateData.value.copy(materno = event.materno, maternoError = null)}
            is WizardScreenEvent.PaternoChanged -> {_uiStateData.value = _uiStateData.value.copy(paterno = event.paterno.uppercase(), paternoError = null)}
            is WizardScreenEvent.NombreChanged -> {_uiStateData.value = _uiStateData.value.copy(nombre = event.nombre.uppercase(), nombreError = null)}
            is WizardScreenEvent.EstadoChanged -> {_uiStateData.value = _uiStateData.value.copy(estado = event.estado, estadoError = null)}
            WizardScreenEvent.StepFechaSubmit -> onSubmitStepFecha()
            WizardScreenEvent.StepGeneroSubmit -> onSubmitStepGenero()
            WizardScreenEvent.StepNombreSubmit -> onSubmitStepName()
            WizardScreenEvent.StepEstadoSubmit -> onSubmitStepEstado()
            WizardScreenEvent.Start -> start()
        }
    }

    private fun onSubmitStepName(){
        val nameResult = addNameUseCase.invoque(_uiStateData.value.nombre)
        val paternoResult = addNameUseCase.invoque(_uiStateData.value.paterno)

        if (nameResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(nombreError = nameResult.error)
        }
        if (paternoResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(paternoError = paternoResult.error)
        }

        if(nameResult is ResultCase.ResultValid && paternoResult is ResultCase.ResultValid){
            _uiWizardState.value = WizardScreenState.StepFecha
        }
    }

    private fun onSubmitStepGenero(){
        val result = addGenderUseCase.invoque(_uiStateData.value.genero.first)
        if(result is ResultCase.ResultError){
            _uiStateData.value = _uiStateData.value.copy(generoError = result.error)
        }

        if(result is ResultCase.ResultValid){
            _uiWizardState.value = WizardScreenState.StepEstado
        }
    }

    private fun onSubmitStepEstado(){
        val result = addStateUseCase.invoque(_uiStateData.value.estado.first)
        if(result is ResultCase.ResultError){
            _uiStateData.value = _uiStateData.value.copy(estadoError = result.error)
        }
        val resultCURP = getCurpUseCase.invoque(_uiStateData.value)
        if(resultCURP is ResultCase.ResultError){
            _uiStateData.value = _uiStateData.value.copy(generoError = resultCURP.error)
        }

        if(resultCURP is ResultCase.ResultSuccess){
            _uiWizardState.value = WizardScreenState.StepDone(
                resultCURP.curp,
                "${_uiStateData.value.nombre} ${_uiStateData.value.paterno} ${_uiStateData.value.materno}"
            )
        }
    }

    private fun onSubmitStepFecha(){
        val result = addBirthUseCase.invoque(_uiStateData.value.fechaNacimiento)
        if(result is ResultCase.ResultError){
            _uiStateData.value = _uiStateData.value.copy(fechaNacimientoError = result.error)
        }

        if(result is ResultCase.ResultValid){
            _uiWizardState.value = WizardScreenState.StepGenero
        }
    }

    private fun back(origin: String, destination: String){
        _uiWizardState.value = WizardScreenState.StateBack(origin,destination)
    }

    private fun start(){
        _uiWizardState.value = WizardScreenState.StepNombre
    }
}