package com.lanazirot.curpavanzado.ui.navgraph

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.provider.GlobalProvider
import com.lanazirot.curpavanzado.screens.states.WizardScreenState

@Composable
fun AppNavigator(globalProvider: GlobalProvider) {

    val navController = globalProvider.nav
    val wizardState = globalProvider.wizardVM
    val state by wizardState.wizardScreenState.collectAsState()

    val context = LocalContext.current


    LaunchedEffect(state) {
        when (state) {
            is WizardScreenState.Welcome -> {
                navController.navigate(Routes.Welcome.route)
            }
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
            is WizardScreenState.ResultScreen -> {
                val stateResult = (state as WizardScreenState.ResultScreen)
                navController.navigate(Routes.WizardResult.getRoute(stateResult.name, stateResult.lastname, stateResult.curp))
            }
            is WizardScreenState.Error -> {
                Toast.makeText(context, (state as WizardScreenState.Error).message, Toast.LENGTH_LONG).show()
            }
            is WizardScreenState.WizardBack -> {
                navController.navigate(route = (state as WizardScreenState.WizardBack).destination) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }

            }
            else -> {}
        }
    }

    NavHost(
        navController = navController, startDestination = Routes.Welcome.route
    ) {
        WelcomeNavGraph()
        ManualNavGraph()
        WizardNavGraph()
        ResultNavGraph()
    }
}