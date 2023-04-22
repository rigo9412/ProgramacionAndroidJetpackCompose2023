package com.curp.ui.global

import androidx.navigation.NavHostController
import com.curp.ui.result.ui.FormViewModel
import com.curp.ui.wizard.ui.WizardViewModel

data class GlobalProvider (
    val formVM: FormViewModel,
    val wizardVM: WizardViewModel,
    val globalVM: GlobalStateScreenViewModel,
    val nav: NavHostController)

sealed class GlobalStateScreen(){
    object FormScreen: GlobalStateScreen()
    object HomeScreen: GlobalStateScreen()
    object ResultScreen: GlobalStateScreen()
    object WizardScreen: GlobalStateScreen()
}