package com.game.curp.ui.wizard

sealed class WizardScreenState {
    object StepNone: WizardScreenState()
    object StepName: WizardScreenState()
    object StepBirth: WizardScreenState()
    object StepGender: WizardScreenState()
    object StepState: WizardScreenState()

    object StepInstruction: WizardScreenState()
    class StepDone(val curp: String,val name: String): WizardScreenState()
    class StateBack(val origin : String,val destination: String): WizardScreenState()

}