package com.myriam.curp2.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.myriam.curp2.ui.HomeScreen
import com.myriam.curp2.ui.form.ui.FormScreen
import com.myriam.curp2.ui.form.ui.FormViewModel
import com.myriam.curp2.ui.result.ui.ResultScreen



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