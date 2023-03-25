package com.example.registrocurp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.registrocurp.curp.ui.form.ui.FormViewModel
import com.example.registrocurp.curp.ui.global.*
import com.example.registrocurp.curp.ui.nav.Navigator
import com.example.registrocurp.curp.ui.wizard.ui.WizardViewModel
import com.example.registrocurp.ui.theme.RegistroCurpTheme

val GlobalProvider = staticCompositionLocalOf<GlobalProvider> { error("No navigation host controller provided.") }
class MainActivity : ComponentActivity() {
    private val globalVM: GlobalStateScreenViewModel by viewModels()
    private val formVM: FormViewModel by viewModels()
    private val wizardVM: WizardViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
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
            RegistroCurpTheme {
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