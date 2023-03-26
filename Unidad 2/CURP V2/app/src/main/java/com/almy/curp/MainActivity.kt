package com.almy.curp

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
import com.almy.curp.ui.HomeScreen
import com.almy.curp.ui.form.ui.FormScreen
import com.almy.curp.ui.form.ui.FormViewModel
import com.almy.curp.ui.result.ui.ResultScreen
import com.almy.curp.ui.nav.Screens
import com.almy.curp.theme.CURPTheme
import com.almy.curp.ui.form.ui.CurpFormModelState
import com.almy.curp.ui.global.GlobalProvider
import com.almy.curp.ui.global.GlobalStateScreenViewModel
import com.almy.curp.ui.nav.Navigator
import com.almy.curp.ui.wizard.ui.*


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


