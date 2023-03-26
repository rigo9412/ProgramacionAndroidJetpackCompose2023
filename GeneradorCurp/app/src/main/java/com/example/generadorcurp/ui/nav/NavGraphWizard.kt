package com.example.generadorcurp.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.generadorcurp.ui.wizard.WizardViewModel
import com.example.generadorcurp.ui.wizard.*

fun NavGraphBuilder.NavGraphWizard(wizardVM: WizardViewModel){
    navigation(
        startDestination = Screens.StepNombreScreen.route,
        route = RoutesGraph.WIZARD.toString()
    ){
        composable(Screens.StepNombreScreen.route) {
            StepNameScreen {
                wizardVM.onEvent(it)
            }
        }
        composable(Screens.StepGeneroScreen.route) {
            StepGeneroScreen {
                wizardVM.onEvent(it)
            }
        }
        composable(Screens.StepFechaScreen.route) {
            StepFechaScreen {
                wizardVM.onEvent(it)
            }
        }
        composable(Screens.StepEstadoScreen.route) {
            StepEstadoScreen() {
                wizardVM.onEvent(it)
            }
        }
        composable(
            Screens.StepInstructionsScreen.route,
            arguments = listOf(
                navArgument("restart") {
                    type = NavType.BoolType
                },
            )
        ) {
            val restart = it.arguments?.getBoolean("restart", false)!!
            if (restart) {
                wizardVM.initState()
            }
            StepInstructionsScreen()
        }
    }
}