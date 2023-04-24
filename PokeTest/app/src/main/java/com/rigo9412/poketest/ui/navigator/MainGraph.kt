package com.rigo9412.poketest.ui.navigator

import androidx.activity.viewModels
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import com.rigo9412.poketest.ui.main.MainScreen
import com.rigo9412.poketest.ui.main.MainViewModel

fun NavGraphBuilder.GraphMain(mainVM: MainViewModel){

    navigation(
        startDestination = Screens.MainScreen.route,
        route = RouterGraph.MAIN.toString()
    ){
        composable(Screens.MainScreen.route) {
            MainScreen(mainVM)
        }
    }
}