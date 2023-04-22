package com.rfcpractica.form.ui

sealed class FormUiState {
    object Init : FormUiState()
    class Loading(val message: String) : FormUiState()

    object Loaded : FormUiState()
    class Done(val curp: String, val name: String) : FormUiState()
    class Error(val message: String) : FormUiState()
    object Finish : FormUiState()
}