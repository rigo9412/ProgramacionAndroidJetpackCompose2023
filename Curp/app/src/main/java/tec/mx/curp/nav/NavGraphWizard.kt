package tec.mx.curp.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import tec.mx.curp.wizard.ui.*

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