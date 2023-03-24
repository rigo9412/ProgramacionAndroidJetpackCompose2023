package com.rigo9412.curp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rigo9412.curp.ui.HomeScreen
import com.rigo9412.curp.ui.form.ui.FormScreen
import com.rigo9412.curp.ui.form.ui.FormViewModel
import com.rigo9412.curp.ui.result.ui.ResultScreen
import com.rigo9412.curp.ui.nav.Screens
import com.rigo9412.curp.theme.CURPTheme
import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.ui.wizard.ui.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            val viewModel: FormViewModel by viewModels()
            val viewModelWizard: WizardViewModel by viewModels()
            val wizardData  by viewModelWizard.uiStateData.collectAsState()


            LaunchedEffect(key1 = context) {
                viewModelWizard.uiWizardState.collect { event ->
                    when(event) {
                        WizardScreenState.StepBirth -> {
                            navController.navigate(Screens.StepBirthScreen.route)
                        }
                        WizardScreenState.StepGender -> {
                            navController.navigate(Screens.StepGenderScreen.route)
                        }
                        is WizardScreenState.StepDone -> {
                            navController.navigate(Screens.Result.route)
                        }
                        WizardScreenState.StepName -> {
                            navController.navigate(Screens.StepNameScreen.route)
                        }
                        WizardScreenState.StepState -> {
                            navController.navigate(Screens.StepStateScreen.route)
                        }
                        is WizardScreenState.StateBack -> {
                            //navController.popBackStack(route = event.destination, inclusive = false)
                            navController.navigate(route = event.destination){
                                popUpTo(event.origin){
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

            CURPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
                        composable(Screens.HomeScreen.route) {
                            HomeScreen(navController)
                        }
                        composable(Screens.StepNameScreen.route) {
                            StepNameScreen(wizardData){
                                viewModelWizard.onEvent(it)
                            }
                        }
                        composable(Screens.StepGenderScreen.route) {
                            StepGenderScreen(wizardData){
                                viewModelWizard.onEvent(it)
                            }
                        }
                        composable(Screens.StepBirthScreen.route) {
                            StepBitrhScreen(wizardData){
                                viewModelWizard.onEvent(it)
                            }
                        }
                        composable(Screens.StepStateScreen.route) {
                            StepStateScreen(wizardData){
                                viewModelWizard.onEvent(it)
                            }
                        }
                        composable(
                            Screens.Form.route,
                            arguments = listOf(
                                navArgument("restart") {
                                    type = NavType.BoolType
                                },
                            )
                        ) {
                            val restart = it.arguments?.getBoolean("restart", false)!!
                            if (restart) {
                                viewModel.initState()
                            }
                            FormScreen(viewModel, navController)
                        }

                        composable(
                            Screens.Result.route,
                            arguments = listOf(
                                navArgument("curp") {
                                    type = NavType.StringType
                                },
                                navArgument("name") {
                                    type = NavType.StringType
                                    defaultValue = "name"
                                },
                                navArgument("lastname") {
                                    type = NavType.StringType
                                    defaultValue = "lastname"
                                }
                            )

                        ) {
                            val curp = it.arguments?.getString("curp", "curp")!!
                            val name = it.arguments?.getString("name")!!
                            val lastname = it.arguments?.getString("name")!!
                            ResultScreen(curp, name + lastname, navController)
                        }
                    }
                }
            }
        }
    }
}


