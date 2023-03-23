package com.lanazirot.curpavanzado


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.screens.components.result.CURPResultScreen
import com.lanazirot.curpavanzado.screens.components.welcome.WelcomeScreen
import com.lanazirot.curpavanzado.screens.components.wizard.BirthdateScreen
import com.lanazirot.curpavanzado.screens.components.wizard.NameScreen
import com.lanazirot.curpavanzado.screens.states.WizardScreenState
import com.lanazirot.curpavanzado.screens.viewmodels.PersonViewModel
import com.lanazirot.curpavanzado.screens.viewmodels.WizardViewModel
import com.lanazirot.curpavanzado.ui.theme.CURPAvanzadoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CURPAvanzadoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var navController = rememberNavController()

                    val wizardViewModel = WizardViewModel()
                    val wizardState by wizardViewModel.wizardScreenState.collectAsState()

                    LaunchedEffect(wizardState) {
                        when (wizardState) {
                            is WizardScreenState.WizardNameScreen -> {
                                navController.navigate(Routes.WizardName.route)
                            }
                            is WizardScreenState.WizardBirthDateScreen -> {
                                navController.navigate(Routes.WizardBirthDate.route)
                            }
                            is WizardScreenState.WizardGenderScreen -> {
                                navController.navigate(Routes.WizardGender.route)
                            }
                            is WizardScreenState.WizardStateScreen -> {
                                navController.navigate(Routes.WizardState.route)
                            }
                            else -> {}
                        }
                    }


                    NavHost(
                        navController = navController,
                        startDestination = Routes.Welcome.route
                    ) {
                        composable(Routes.Welcome.route) { WelcomeScreen(navController = navController) }
                        composable(Routes.Manual.route) { Text(text = "Manual") }
                        composable(Routes.WizardName.route) {
                            val parentEntry = remember(it) {
                                navController.getBackStackEntry(Routes.WizardName.route)
                            }
                            val personViewModel = hiltViewModel<PersonViewModel>(parentEntry)
                            NameScreen(
                                personViewModel = personViewModel,
                                onNext = {
                                    Log.d("Wizard", "onNext: $it")
                                    wizardViewModel.onSubmit(currentScreen = WizardScreenState.WizardNameScreen())
                                }) {
                                navController.navigate(Routes.Welcome.route)
                            }
                        }
                        composable(Routes.WizardBirthDate.route) {
                            val parentEntry = remember(it) {
                                navController.getBackStackEntry(Routes.WizardName.route)
                            }
                            val personViewModel = hiltViewModel<PersonViewModel>(parentEntry)
                            BirthdateScreen(
                                personViewModel = personViewModel, onNext = {

                                }) {
                                navController.popBackStack()
                            }
                        }
                        composable(Routes.WizardGender.route) { Text(text = "CURP") }
                        composable(Routes.WizardState.route) { Text(text = "CURP") }
                        composable(Routes.WizardResult.route) {
                            CURPResultScreen(
                                curp = it.arguments?.getString("curp") ?: "",
                                name = it.arguments?.getString("name") ?: "",
                            )
                        }
                    }
                }
            }
        }
    }
}

