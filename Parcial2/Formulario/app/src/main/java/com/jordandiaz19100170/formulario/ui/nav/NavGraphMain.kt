package com.jordandiaz19100170.formulario.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.jordandiaz19100170.formulario.ui.HomeScreen


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