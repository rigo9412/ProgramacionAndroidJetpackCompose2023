package com.jordandiaz19100170.formulario.ui.global

import androidx.navigation.NavHostController
import com.jordandiaz19100170.formulario.ui.form.ui.FormViewModel
import com.jordandiaz19100170.formulario.ui.wizard.ui.WizardViewModel

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



