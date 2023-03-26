package com.jordandiaz19100170.formulario.ui.wizard.ui


sealed class WizardScreenEvent {
    data class NameChanged(val name:String) : WizardScreenEvent()
    data class MiddleNameChanged(val middleName:String) : WizardScreenEvent()
    data class LastNameChanged(val lastName:String) : WizardScreenEvent()
    data class BirthChanged(val birth:String) : WizardScreenEvent()
    data class GenderChanged(val gender:Pair<String,String>) : WizardScreenEvent()
    data class StateChanged(val state:Pair<String,String>) : WizardScreenEvent()
    data class Back(val origin: String, val destination: String) : WizardScreenEvent()

    object StepNameSubmit : WizardScreenEvent()
    object StepBirthSubmit: WizardScreenEvent()
    object StepGenderSubmit : WizardScreenEvent()
    object StepStateSubmit : WizardScreenEvent()
    object Start : WizardScreenEvent()



}