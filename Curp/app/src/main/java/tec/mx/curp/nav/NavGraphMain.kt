package tec.mx.curp.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tec.mx.curp.wizard.ui.HomeScreen

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