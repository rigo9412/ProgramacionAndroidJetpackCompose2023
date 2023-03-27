package com.example.generadorcurp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.generadorcurp.ui.form.ui.FormViewModel
import com.example.generadorcurp.ui.global.GlobalProvider
import com.example.generadorcurp.ui.global.GlobalStateScreenViewModel
import com.example.generadorcurp.ui.nav.Navigator
import com.example.generadorcurp.ui.theme.GeneradorCurpTheme
import com.example.generadorcurp.ui.wizard.WizardViewModel

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

            GeneradorCurpTheme {
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
