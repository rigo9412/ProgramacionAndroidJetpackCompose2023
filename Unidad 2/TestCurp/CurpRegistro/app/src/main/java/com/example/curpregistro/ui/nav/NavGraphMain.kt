package com.example.curpregistro.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.curpregistro.ui.HomeScreen




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