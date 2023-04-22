package com.rfcpractica.curp.ui.global

import androidx.navigation.NavHostController
import com.rfcpractica.curp.ui.wizard.ui.WizardViewModel
import com.rfcpractica.curp.ui.form.ui.FormViewModel

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



