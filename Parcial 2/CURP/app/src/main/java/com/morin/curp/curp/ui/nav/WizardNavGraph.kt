package com.morin.curp.curp.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.morin.curp.curp.ui.wizard.ui.*
import com.morin.curp.wizard.ui.StepInstructionsScreen


fun NavGraphBuilder.WizardNavGraph(wizardVM: WizardViewModel){
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