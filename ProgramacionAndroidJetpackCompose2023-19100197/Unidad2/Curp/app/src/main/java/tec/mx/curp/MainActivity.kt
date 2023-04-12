package tec.mx.curp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import tec.mx.curp.form.ui.FormViewModel
import tec.mx.curp.ui.global.GlobalProvider
import tec.mx.curp.ui.global.GlobalStateScreenViewModel
import tec.mx.curp.domain.nav.Navigator
import tec.mx.curp.ui.theme.CurpTheme
import tec.mx.curp.ui.wizard.ui.WizardViewModel

val GlobalProvider = compositionLocalOf<GlobalProvider> { error("No navigation host controller provided.") }
class MainActivity : ComponentActivity() {
    private val globalVM: GlobalStateScreenViewModel by viewModels()
    private val formVM: FormViewModel by viewModels()
    private val wizardVM: WizardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val gp = GlobalProvider(
                formVM = formVM,
                wizardVM = wizardVM,
                globalVM = globalVM,
                nav = navController
            )
            CurpTheme {
                CompositionLocalProvider(GlobalProvider provides gp) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Navigator(gp.nav,gp.formVM,gp.wizardVM)
                    }
                }
            }

        }
    }

}