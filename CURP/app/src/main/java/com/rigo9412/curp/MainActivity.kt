package com.rigo9412.curp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rigo9412.curp.ui.HomeScreen
import com.rigo9412.curp.ui.form.ui.FormScreen
import com.rigo9412.curp.ui.form.ui.FormViewModel
import com.rigo9412.curp.ui.result.ui.ResultScreen
import com.rigo9412.curp.ui.nav.Screens
import com.rigo9412.curp.theme.CURPTheme
import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.ui.global.GlobalProvider
import com.rigo9412.curp.ui.global.GlobalStateScreenViewModel
import com.rigo9412.curp.ui.nav.Navigator
import com.rigo9412.curp.ui.wizard.ui.*


val GlobalProvider = staticCompositionLocalOf<GlobalProvider> { error("No navigation host controller provided.") }
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
            CURPTheme {
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


