package com.lanazirot.curpgenerator.screens.curp.viewmodel.state

sealed class CURPUIState {
    object Loading : CURPUIState()
    object Success : CURPUIState()
    data class Error(val message: String) : CURPUIState()
    data class Loaded(val message: String) : CURPUIState()
    object Valid : CURPUIState()
}