package com.lanazirot.curpavanzado.domain.events

sealed class WizardScreenEvent {
    data class Back(val origin: String, val destination: String) : WizardScreenEvent()
    object StepNameSubmit : WizardScreenEvent()
    object StepBirthSubmit: WizardScreenEvent()
    object StepGenderSubmit : WizardScreenEvent()
    object StepStateSubmit : WizardScreenEvent()
}