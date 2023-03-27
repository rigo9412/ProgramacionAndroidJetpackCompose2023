package com.example.generadorcurp.ui.global

import androidx.navigation.NavHostController
import com.example.generadorcurp.ui.form.ui.FormViewModel
import com.example.generadorcurp.ui.wizard.WizardViewModel


data class GlobalProvider (
    val formVM: FormViewModel,
    val wizardVM: WizardViewModel,
    val globalVM: GlobalStateScreenViewModel,
    val nav: NavHostController
)

sealed class GlobalStateScreen(){
    object FormScreen: GlobalStateScreen()
    object HomeScreen: GlobalStateScreen()
    object ResultScreen: GlobalStateScreen()
    object WizardScreen: GlobalStateScreen()
}