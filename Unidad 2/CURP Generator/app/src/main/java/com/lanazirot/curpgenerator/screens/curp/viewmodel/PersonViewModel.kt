package com.lanazirot.curpgenerator.screens.curp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lanazirot.curpgenerator.domain.enums.blackListWords
import com.lanazirot.curpgenerator.domain.models.Person
import com.lanazirot.curpgenerator.screens.curp.viewmodel.state.CURPUIState
import com.lanazirot.curpgenerator.screens.curp.viewmodel.state.PersonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.abs

class PersonViewModel : ViewModel() {
    private var _personState = MutableStateFlow(PersonState())
    var personState: StateFlow<PersonState> = _personState.asStateFlow()

    private var _uiState = MutableStateFlow<CURPUIState>(CURPUIState.Loading)
    var uiState: StateFlow<CURPUIState> = _uiState.asStateFlow()

    init {
        _personState.value = PersonState()
        _uiState.value = CURPUIState.Loaded
    }

    fun onValidForm(): Boolean {
        val state = _personState.value.person
        if (state.name.isEmpty()) {
            _uiState.value = CURPUIState.Error("El nombre no puede estar vacío")
            return false
        }
        if (state.surname.isEmpty()) {
            _uiState.value = CURPUIState.Error("El apellido paterno no puede estar vacío")
            return false
        }
        if (state.birthDate.isEmpty()) {
            _uiState.value = CURPUIState.Error("La fecha de nacimiento no puede estar vacía")
            return false
        }
        return true
    }

    fun updatePerson(updatedPerson: Person) {
        _personState.value = _personState.value.copy(person = updatedPerson)
        onValidForm()
    }

    private fun checkBlackListCURP(curp: String): String {
        return if (blackListWords.contains(curp.substring(0, 4))) curp.replaceRange(
            1,
            2,
            "X"
        ) else curp
    }


    private fun digitoVerificador(curp: String): Int {
        val diccionario = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        var suma = 0.0
        for (i in 0 until 17) {
            suma += diccionario.indexOf(curp[i]) * (18 - i)
        }
        val digito: Double = 10 - suma % 10
        if (digito == 10.0) return 0
        return abs(digito.toInt())
    }

    fun generateCURP(): String {
        val state = _personState.value.person
        var curp = ""
        curp += state.lastname.substring(0, 1)
        curp += state.lastname.slice(1..2).replace(regex = Regex("[^A|E|I|O|U]"), replacement = "")[0]
        curp += state.surname.substring(0, 1)
        curp += state.name.substring(0, 1)
        curp += state.birthDate.substring(2, 4)
        curp += state.birthDate.substring(5, 7)
        curp += state.birthDate.substring(8, 10)
        curp += state.gender.code
        curp += state.state.abbreviation
        curp += state.lastname.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        curp += state.surname.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")[0]
        curp += state.name.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        curp += if (state.birthDate.substring(0, 4).toInt() < 2000) "1" else "A"
        curp += digitoVerificador(curp)
        return checkBlackListCURP(curp.replace("Ñ", "X").uppercase())
    }
}