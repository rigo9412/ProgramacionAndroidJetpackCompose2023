package com.lanazirot.curpavanzado.screens.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lanazirot.curpavanzado.screens.states.PersonState
import com.lanazirot.curpgenerator.domain.models.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor() : ViewModel() {
    private var _personState = MutableStateFlow(PersonState())
    var personState: StateFlow<PersonState> = _personState.asStateFlow()

    fun updatePerson(updatedPerson: Person) {
        _personState.value = _personState.value.copy(person = updatedPerson)
        Log.d("PersonViewModel", "updatePerson: ${_personState.value.person}")
    }

}