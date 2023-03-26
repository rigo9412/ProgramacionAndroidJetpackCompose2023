package com.game.curp.ui.wizard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.game.curp.domain.*
import com.game.curp.ui.form.CurpFormModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.ArrayList

class WizardViewModel(
    private val addNameUseCase: AddNameUseCase = AddNameUseCase(),
    private val addBirthUseCase: AddBirthUseCase = AddBirthUseCase(),
    private val addGenderCase: AddGenderCase = AddGenderCase(),
    private val addStateUseCase: AddStateUseCase = AddStateUseCase(),
    private val getCurpUseCase: GetCurpUseCase = GetCurpUseCase()
) : ViewModel() {
    private val _uiWizardState = MutableStateFlow<WizardScreenState>(WizardScreenState.StepNone)
    val uiWizardState: StateFlow<WizardScreenState> = _uiWizardState

    private val _uiStateData = MutableStateFlow<CurpFormModelState>(CurpFormModelState())
    val uiStateData: StateFlow<CurpFormModelState> = _uiStateData

    fun initState() {
        println("CURP-WIZARD-STATE INIT")
        _uiStateData.value = CurpFormModelState(
            name = "test",
            sexList = getGenders(),
            statesList = getStates(),
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: WizardScreenEvent) {
        when (event) {
            is WizardScreenEvent.Back -> back(event.origin, event.destination)

            is WizardScreenEvent.BirthChanged -> {
                _uiStateData.value = _uiStateData.value.copy(birth = event.birth, birthError = null)
            }
            is WizardScreenEvent.GenderChanged -> {
                _uiStateData.value =
                    _uiStateData.value.copy(gender = event.gender, genderError = null)
            }
            is WizardScreenEvent.LastNameChanged -> {
                _uiStateData.value = _uiStateData.value.copy(
                    lastName = event.lastName.uppercase(),
                    lastNameError = null
                )
            }
            is WizardScreenEvent.MiddleNameChanged -> {
                _uiStateData.value = _uiStateData.value.copy(
                    middleName = event.middleName.uppercase(),
                    middleNameError = null
                )
            }
            is WizardScreenEvent.NameChanged -> {
                _uiStateData.value =
                    _uiStateData.value.copy(name = event.name.uppercase(), nameError = null)
            }
            is WizardScreenEvent.StateChanged -> {
                _uiStateData.value = _uiStateData.value.copy(state = event.state, stateError = null)
            }

            WizardScreenEvent.StepBirthSubmit -> onSubmitStepBirth()
            WizardScreenEvent.StepGenderSubmit -> onSubmitStepGender()
            WizardScreenEvent.StepNameSubmit -> onSubmitStepName()
            WizardScreenEvent.StepStateSubmit -> onSubmitStepState()
            WizardScreenEvent.Start -> start()
            else -> {}
        }
    }

    private fun onSubmitStepName() {

        val nameResult = addNameUseCase.invoque(_uiStateData.value.name)
        val middleNameResult = addNameUseCase.invoque(_uiStateData.value.middleName)

        if (nameResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(nameError = nameResult.error)
        }
        if (middleNameResult is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(middleNameError = middleNameResult.error)
        }

        if (nameResult is ResultCase.ResultValid &&
            middleNameResult is ResultCase.ResultValid
        ) {
            _uiWizardState.value = WizardScreenState.StepBirth
        }


    }

    private fun onSubmitStepGender() {
        val result = addGenderCase.invoque(_uiStateData.value.gender.first)
        if (result is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(genderError = result.error)
        }

        if (result is ResultCase.ResultValid
        ) {
            _uiWizardState.value = WizardScreenState.StepState
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onSubmitStepState() {
        val result = addStateUseCase.invoque(_uiStateData.value.state.first)
        if (result is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(stateError = result.error)
        }
        val resultCURP = getCurpUseCase.invoque(_uiStateData.value)
        if (resultCURP is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(genderError = resultCURP.error)
        }

        if (resultCURP is ResultCase.ResultSuccess) {
            _uiWizardState.value = WizardScreenState.StepDone(
                resultCURP.curp,
                "${_uiStateData.value.name} ${_uiStateData.value.middleName} ${_uiStateData.value.lastName}"
            )
        }
    }

    private fun onSubmitStepBirth() {
        val result = addBirthUseCase.invoque(_uiStateData.value.birth)
        if (result is ResultCase.ResultError) {
            _uiStateData.value = _uiStateData.value.copy(birthError = result.error)
        }

        if (result is ResultCase.ResultValid) {
            _uiWizardState.value = WizardScreenState.StepGender
        }
    }

    private fun back(origin: String, destination: String) {
        _uiWizardState.value = WizardScreenState.StateBack(origin, destination)
    }

    private fun start() {
        _uiWizardState.value = WizardScreenState.StepName
    }

    private fun getGenders(): ArrayList<Pair<String, String>> {
        //todo simular un delay
        return sexos
    }

    private fun getStates(): ArrayList<Pair<String, String>> {
        //todo simular un delay
        return estados
    }
}