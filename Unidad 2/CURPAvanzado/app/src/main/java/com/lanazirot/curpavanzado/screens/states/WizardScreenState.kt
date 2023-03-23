package com.lanazirot.curpavanzado.screens.states

open class WizardScreenState(
    val personState: PersonState = PersonState()
) {
    class Welcome : WizardScreenState()
    class Manual : WizardScreenState()
    class WizardNameScreen(): WizardScreenState()
    class WizardBirthDateScreen : WizardScreenState()
    class WizardGenderScreen : WizardScreenState()
    class WizardStateScreen : WizardScreenState()
    class ResultScreen() : WizardScreenState()
    class Error(val message: String) : WizardScreenState()
}