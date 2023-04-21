package com.example.simondiceviewmodel.screeens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class Top10ViewModel: ViewModel() {
    var state by mutableStateOf(Top10State())
    private set

    init {
        viewModelScope.launch{
            state = state.copy(
//                isLoading = true
            )
            delay(2000)
//            state.records = listOf();
            state = state.copy(
                records = listOf(
                    Jugador("Juan",120,5),
                    Jugador("Juan",120,5),
                    Jugador("Juan",120,5),
                ),
//                isLoading = false
            )
        }
    }
}