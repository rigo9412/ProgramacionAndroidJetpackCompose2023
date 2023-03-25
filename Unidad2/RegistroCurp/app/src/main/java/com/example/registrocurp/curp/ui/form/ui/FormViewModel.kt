package com.example.registrocurp.curp.ui.form.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.registrocurp.curp.data.*
import com.example.registrocurp.curp.domain.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class FormViewModel(
    private val addNameUseCase: AddNameUseCase = AddNameUseCase(),
    private val addBirthUseCase: AddBirthUseCase = AddBirthUseCase(),
    private val addGenderCase: AddGenderCase = AddGenderCase(),
    private val addStateUseCase: AddStateUseCase = AddStateUseCase(),
    private val getCurpUseCase: GetCurpUseCase = GetCurpUseCase()

) : ViewModel() {

    private val _uiState =
        MutableStateFlow<FormCurpScreenState>(FormCurpScreenState.Init)
    val uiState: StateFlow<FormCurpScreenState> = _uiState

    private val _uiStateData = MutableStateFlow<CurpFormModelState>(CurpFormModelState())
    val uiStateData: StateFlow<CurpFormModelState> = _uiStateData


    init {
        initState()
    }

    fun initState() {

        _uiState.value = FormCurpScreenState.Loading("cargando...estamos trabajando...")
        _uiStateData.value = CurpFormModelState(
            name = "",
            middleName = "",
            lastName = "",
            birth = "2023-03-01",
            state = Pair("AS", "Aguascalientes"),
            gender = Pair("M", "Mujer"),
            sexList = getGenders(),
            statesList = getStates(),
        )

        Timer().schedule(timerTask {
            _uiState.value = FormCurpScreenState.Loaded
        }, 1000)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: CurpFormEvent) {
        when (event) {
            is CurpFormEvent.BirthChanged -> {
                _uiStateData.value = _uiStateData.value.copy(birth = event.birth)
            }
            is CurpFormEvent.GenderChanged -> {
                _uiStateData.value = _uiStateData.value.copy(gender = event.gender)
            }
            is CurpFormEvent.LastNameChanged -> {
                _uiStateData.value = _uiStateData.value.copy(lastName = event.lastName.uppercase())
            }
            is CurpFormEvent.MiddleNameChanged -> {
                _uiStateData.value =
                    _uiStateData.value.copy(middleName = event.middleName.uppercase())
            }
            is CurpFormEvent.NameChanged -> {
                _uiStateData.value = _uiStateData.value.copy(name = event.name.uppercase())
            }
            is CurpFormEvent.StateChanged -> {
                _uiStateData.value = _uiStateData.value.copy(state = event.state)
            }
            is CurpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun submitData() {
        _uiState.value = FormCurpScreenState.Loading("cargando...estamos trabajando...")
        val nameResult = addNameUseCase.invoque(_uiStateData.value.name)
        val middleNameResult = addNameUseCase.invoque(_uiStateData.value.middleName)
        val genderResult = addGenderCase.invoque(_uiStateData.value.gender.first)
        val stateResult = addStateUseCase.invoque(_uiStateData.value.state.first)
        val birthResult = addBirthUseCase.invoque(_uiStateData.value.birth)

        if (nameResult is ResultCase.ResultError ) {
            _uiStateData.value = _uiStateData.value.copy(nameError = nameResult.error)
        }
        if (middleNameResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(middleNameError = middleNameResult.error)
        }
        if (genderResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(genderError = genderResult.error)
        }
        if (birthResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(stateError = birthResult.error)
        }
        if (stateResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(stateError = stateResult.error)
        }
        else if (_uiStateData.value.name.isEmpty()){
            _uiState.value = FormCurpScreenState.Error(message = "Nombre vacio")
        }
        else if (_uiStateData.value.middleName.isEmpty()){
            _uiState.value = FormCurpScreenState.Error(message = "Primer apellido vacio")
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
                    _uiState.value = FormCurpScreenState.Done(result.curp, _uiStateData.value.name)
                }, 1200)
            } else if (result is ResultCase.ResultError) {
                _uiState.value = FormCurpScreenState.Error(message = result.error)
            }
        }
    }

    private fun getGenders(): ArrayList<Pair<String, String>> {
        //todo simular un delay
        return generos
    }

    private fun getStates(): ArrayList<Pair<String, String>> {
        //todo simular un delay
        return estados
    }
}



