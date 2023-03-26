package com.example.generadorcurp.ui.wizard

sealed class WizardScreenEvent {
    data class NombreChanged(val nombre: String): WizardScreenEvent()
    data class PaternoChanged(val paterno: String): WizardScreenEvent()
    data class MaternoChanged(val materno: String): WizardScreenEvent()
    data class FechaChanged(val fecha: String): WizardScreenEvent()
    data class EstadoChanged(val estado: Pair<String,String>): WizardScreenEvent()
    data class GeneroChanged(val genero: Pair<String,String>): WizardScreenEvent()
    data class Back(val origin: String, val destination: String): WizardScreenEvent()

    object StepNombreSubmit: WizardScreenEvent()
    object StepFechaSubmit: WizardScreenEvent()
    object StepGeneroSubmit: WizardScreenEvent()
    object StepEstadoSubmit: WizardScreenEvent()
    object Start: WizardScreenEvent()
}