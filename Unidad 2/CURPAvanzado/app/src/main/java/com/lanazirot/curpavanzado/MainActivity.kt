package com.lanazirot.curpavanzado


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lanazirot.curpavanzado.provider.GlobalProvider
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider
import com.lanazirot.curpavanzado.screens.viewmodels.WizardViewModel
import com.lanazirot.curpavanzado.ui.navgraph.AppNavigator
import com.lanazirot.curpavanzado.ui.theme.CURPAvanzadoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val wizardVM: WizardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()
            val gp = GlobalProvider(wizardVM = wizardVM, nav = navController)
            CURPAvanzadoTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                    ) {
                        AppNavigator(globalProvider = gp)
                    }
                }
            }
        }
    }
}

