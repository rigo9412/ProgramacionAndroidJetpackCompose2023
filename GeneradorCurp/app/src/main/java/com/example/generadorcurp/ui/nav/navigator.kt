package com.example.generadorcurp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.example.generadorcurp.ui.form.ui.FormViewModel
import com.example.generadorcurp.ui.wizard.WizardScreenState
import com.example.generadorcurp.ui.wizard.WizardViewModel

@Composable
fun Navigator(navController: NavHostController, formViewModel: FormViewModel, wizardVM: WizardViewModel){
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        wizardVM.uiWizardState.collect { event ->
            when (event) {
                WizardScreenState.StepFecha -> {
                    navController.navigate(Screens.StepFechaScreen.route)
                }
                WizardScreenState.StepInstruction -> {
                    navController.navigate(Screens.StepInstructionsScreen.generateRoute(true))
                }
                WizardScreenState.StepGenero -> {
                    navController.navigate(Screens.StepGeneroScreen.route)
                }
                is WizardScreenState.StepDone -> {
                    navController.navigate(Screens.Result.generateRoute(event.curp,event.nombre))
                }
                WizardScreenState.StepNombre -> {
                    navController.navigate(Screens.StepNombreScreen.route)
                }
                WizardScreenState.StepEstado -> {
                    navController.navigate(Screens.StepEstadoScreen.route)
                }
                is WizardScreenState.StateBack -> {
                    navController.navigate(route = event.destination) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
                else -> {
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