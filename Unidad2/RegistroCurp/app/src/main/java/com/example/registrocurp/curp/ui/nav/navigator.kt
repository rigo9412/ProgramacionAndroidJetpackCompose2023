package com.example.registrocurp.curp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.registrocurp.curp.ui.HomeScreen
import com.example.registrocurp.curp.ui.form.ui.FormViewModel
import com.example.registrocurp.curp.ui.wizard.ui.WizardScreenState
import com.example.registrocurp.curp.ui.wizard.ui.WizardViewModel

@Composable
fun Navigator(navController: NavHostController, formViewModel: FormViewModel, wizardVM: WizardViewModel){
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
                    navController.navigate(Screens.Result.generateRoute(event.curp, event.name))
                }
                WizardScreenState.StepName -> {
                    navController.navigate(Screens.StepNameScreen.route)
                }
                WizardScreenState.StepState -> {
                    navController.navigate(Screens.StepStateScreen.route)
                }
                is WizardScreenState.StateBack -> {
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
        startDestination = Screens.HomeScreen.route,
        route = RoutesGraph.ROOT.toString()
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }
        MainNavGraph(formViewModel)
        WizardNavGraph(wizardVM)
    }
}