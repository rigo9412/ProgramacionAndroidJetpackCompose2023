package com.rigo9412.curp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rigo9412.curp.ui.HomeScreen
import com.rigo9412.curp.ui.form.ui.FormScreen
import com.rigo9412.curp.ui.result.ui.ResultScreen
import com.rigo9412.curp.ui.wizard.ui.StepNameScreen

@Composable
fun Navigator(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

    }
}