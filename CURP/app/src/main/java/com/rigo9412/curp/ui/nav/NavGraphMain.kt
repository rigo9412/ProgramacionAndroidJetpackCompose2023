package com.rigo9412.curp.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.rigo9412.curp.ui.HomeScreen


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