package com.example.simondice.models

import com.example.simondice.domain.models.Player

sealed class UiState {

    object Loading : UiState()

    class Error(val message: String) : UiState()

    class Ready(var top: List<Player>) : UiState()

}
