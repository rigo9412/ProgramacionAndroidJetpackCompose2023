package com.otop.CURPGenerator.ui.form.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.otop.CURPGenerator.ui.wizard.HomeScreen

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