package com.curp.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.curp.ui.HomeScreen
import com.curp.ui.form.ui.FormScreen
import com.curp.ui.form.ui.FormViewModel
import com.curp.ui.result.ui.ResultScreen



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