package tec.mx.curp.curp.ui.global

import androidx.navigation.NavHostController
import tec.mx.curp.form.ui.FormViewModel
import tec.mx.curp.curp.ui.wizard.ui.WizardViewModel

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