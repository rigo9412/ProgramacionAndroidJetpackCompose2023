package com.otop.CURPGenerator.ui.global

import androidx.navigation.NavHostController
import com.otop.CURPGenerator.ui.form.formui.FormViewModel
import com.otop.CURPGenerator.ui.wizard.WizardViewModel

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

