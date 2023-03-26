package com.example.generadorcurp.ui.wizard

sealed class WizardScreenState {
    object StepNone: WizardScreenState()
    object StepNombre: WizardScreenState()
    object StepFecha: WizardScreenState()
    object StepGenero: WizardScreenState()
    object StepEstado: WizardScreenState()

    object StepInstruction: WizardScreenState()

    class StepDone(val curp: String, val nombre: String) : WizardScreenState()
    class StateBack(val origin: String, val destination: String): WizardScreenState()
}