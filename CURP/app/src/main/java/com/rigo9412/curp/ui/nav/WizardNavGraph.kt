package com.rigo9412.curp.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.ui.wizard.ui.StepNameScreen
import com.rigo9412.curp.ui.wizard.ui.WizardViewModel


fun NavGraphBuilder.WizardNavGraph(navController: NavHostController,viewModelWizard: WizardViewModel, wizardData: CurpFormModelState,){
    navigation(
        startDestination = Screens.StepNameScreen.route,
        route = RoutesGraph.WIZARD.toString()
    ){
        composable(Screens.StepNameScreen.route) {
            StepNameScreen(wizardData){
                viewModelWizard.onEvent(it)
            }
        }
        composable(Screens.StepGenderScreen.route) {
            StepNameScreen(wizardData){
                viewModelWizard.onEvent(it)
            }
        }
        composable(Screens.StepBirthScreen.route) {
            StepNameScreen(wizardData){
                viewModelWizard.onEvent(it)
            }
        }
        composable(Screens.StepStateScreen.route) {
            StepNameScreen(wizardData){
                viewModelWizard.onEvent(it)
            }
        }
    }
}