package tec.mx.curp.form.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tec.mx.curp.data.estados
import tec.mx.curp.data.generos
import tec.mx.curp.domain.*
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
        MutableStateFlow<FormCurpScreenState>(FormCurpScreenState.Init)
    val uiState: StateFlow<FormCurpScreenState> = _uiState

    private val _uiStateData = MutableStateFlow<CurpFormModelState>(CurpFormModelState())
    val uiStateData: StateFlow<CurpFormModelState> = _uiStateData

    // Estado Incial del Generar CURP en el todo en uno
    fun initState() {
        println("CURP-FORM-STATE INIT")
        _uiState.value = FormCurpScreenState.Loading("Cargando...")
        _uiStateData.value = CurpFormModelState(
            name = "",
            sexList = getGenders(),
            statesList = getStates(),
        )
        Timer().schedule(timerTask {
            _uiState.value = FormCurpScreenState.Loaded
        }, 1000)
    }


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
            CurpFormEvent.Hide -> _uiState.value = FormCurpScreenState.Loaded
        }
    }

    private fun submitData() {
        _uiState.value = FormCurpScreenState.Loading("cargando...")
        val nameResult = addNameUseCase.invoque(_uiStateData.value.name)
        val middleNameResult = addNameUseCase.invoque(_uiStateData.value.middleName)
        val genderResult = addGenderCase.invoque(_uiStateData.value.gender.first)
        val stateResult = addStateUseCase.invoque(_uiStateData.value.state.first)
        val birthResult = addBirthUseCase.invoque(_uiStateData.value.birth)

        if (nameResult is ResultCase.ResultError) {
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

            }

        }else{
            _uiState.value = FormCurpScreenState.Error(message = "Ocurrio un problema")
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
