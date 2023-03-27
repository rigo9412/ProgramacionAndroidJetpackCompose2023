package com.program.nuevocurp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.program.nuevocurp.ui.form.ui.FormViewModel
import com.program.nuevocurp.ui.global.GlobalProvider
import com.program.nuevocurp.ui.global.GlobalStateScreenViewModel
import com.program.nuevocurp.ui.nav.Navigator
import com.program.nuevocurp.ui.theme.NuevoCurpTheme
import com.program.nuevocurp.ui.*
import com.program.nuevocurp.ui.wizard.ui.WizardViewModel

val GlobalProvider = compositionLocalOf<GlobalProvider> { error("No navigation host controller provided.") }

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

            NuevoCurpTheme {
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

