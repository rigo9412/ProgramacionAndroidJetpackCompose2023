package com.ezequiel.curp.ui.global

import androidx.navigation.NavHostController
import com.ezequiel.curp.ui.form.ui.FormViewModel
import com.ezequiel.curp.ui.wizard.ui.WizardViewModel

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



