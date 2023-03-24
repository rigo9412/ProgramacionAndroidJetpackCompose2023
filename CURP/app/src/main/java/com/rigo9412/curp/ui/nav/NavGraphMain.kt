package com.rigo9412.curp.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.rigo9412.curp.ui.HomeScreen
import com.rigo9412.curp.ui.form.ui.FormScreen
import com.rigo9412.curp.ui.form.ui.FormViewModel
import com.rigo9412.curp.ui.result.ui.ResultScreen



fun NavGraphBuilder.NavGraphMain(){

    navigation(
        startDestination = Screens.HomeScreen.route,
        route = RoutesGraph.MAIN.toString()
    ){
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }
    }
}