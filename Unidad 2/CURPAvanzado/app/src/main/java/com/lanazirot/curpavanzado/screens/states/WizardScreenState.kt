package com.lanazirot.curpavanzado.screens.states

sealed class WizardScreenState{
    object Welcome : WizardScreenState()
    object Manual : WizardScreenState()
    object WizardNameScreen : WizardScreenState()
    object WizardBirthDateScreen: WizardScreenState()
    object WizardGenderScreen : WizardScreenState()
    object WizardStateScreen : WizardScreenState()
    class ResultScreen(val curp: String, val name: String, val lastname: String) : WizardScreenState()
    class WizardBack(val origin: String, val destination: String) : WizardScreenState()

    class Error(val message: String) : WizardScreenState()
}