package com.ezequiel.simondice.models

import com.ezequiel.simondice.domain.modelo.Player

sealed class UiState {

    object Loading : UiState()

    class Error(val message: String) : UiState()

    class Ready(var top: List<Player>) : UiState()

}
