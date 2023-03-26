package com.example.generadorcurp.form.domain.ui

sealed class CurpFormEvent {
    data class NombreChanged(val nombre: String): CurpFormEvent()
    data class PaternoChanged(val paterno: String) : CurpFormEvent()
    data class MaternoChanged(val materno: String) : CurpFormEvent()
    data class FechaChanged(val fecha: String): CurpFormEvent()
    data class EstadoChanged(val estado: String): CurpFormEvent()

    object Submit : CurpFormEvent()
    object Hide : CurpFormEvent()
}