package com.rigo9412.curp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rigo9412.curp.ui.HomeScreen
import com.rigo9412.curp.ui.form.ui.FormScreen
import com.rigo9412.curp.ui.form.ui.FormViewModel
import com.rigo9412.curp.ui.result.ui.ResultScreen
import com.rigo9412.curp.ui.wizard.ui.StepNameScreen
import com.rigo9412.curp.ui.wizard.ui.WizardScreenState
import com.rigo9412.curp.ui.wizard.ui.WizardViewModel

@Composable
fun Navigator(navController: NavHostController,formViewModel: FormViewModel,wizardVM: WizardViewModel){
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        wizardVM.uiWizardState.collect { event ->
            when (event) {
                WizardScreenState.StepBirth -> {
                    navController.navigate(Screens.StepBirthScreen.route)
                }
                WizardScreenState.StepInstruction -> {
                    navController.navigate(Screens.StepInstructionsScreen.generateRoute(true))
                }
                WizardScreenState.StepGender -> {
                    navController.navigate(Screens.StepGenderScreen.route)
                }
                is WizardScreenState.StepDone -> {
                    navController.navigate(Screens.Result.generateRoute(event.curp,event.name))
                }
                WizardScreenState.StepName -> {
                    navController.navigate(Screens.StepNameScreen.route)
                }
                WizardScreenState.StepState -> {
                    navController.navigate(Screens.StepStateScreen.route)
                }
                is WizardScreenState.StateBack -> {
//                            navController.popBackStack(
//                                route = event.destination,
//                                inclusive = false)
                    navController.navigate(route = event.destination) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
                else -> {
                    //StepNone
                }

            }
        }
    }


    NavHost(navController = navController,
        startDestination = RoutesGraph.MAIN.toString(),
        route = RoutesGraph.ROOT.toString()
    ) {
        NavGraphMain()
        NavGraphForm(formViewModel)
        NavGraphWizard(wizardVM)
    }
}