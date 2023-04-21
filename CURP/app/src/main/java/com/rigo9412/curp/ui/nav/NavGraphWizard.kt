package com.rigo9412.curp.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.rigo9412.curp.ui.wizard.ui.*


fun NavGraphBuilder.NavGraphWizard(wizardVM: WizardViewModel){
    navigation(
        startDestination = Screens.StepNameScreen.route,
        route = RoutesGraph.WIZARD.toString()
    ){
        composable(Screens.StepNameScreen.route) {
            StepNameScreen {
                wizardVM.onEvent(it)
            }
        }
        composable(Screens.StepGenderScreen.route) {
            StepGenderScreen {
                wizardVM.onEvent(it)
            }
        }
        composable(Screens.StepBirthScreen.route) {
            StepBitrhScreen {
                wizardVM.onEvent(it)
            }
        }
        composable(Screens.StepStateScreen.route) {
            StepStateScreen() {
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