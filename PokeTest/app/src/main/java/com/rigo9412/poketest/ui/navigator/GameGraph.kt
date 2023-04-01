package com.rigo9412.poketest.ui.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.compose.composable

fun NavGraphBuilder.GraphGame(){

    navigation(
        startDestination = Screens.GameScreen.route,
        route = RouterGraph.GAME.toString()
    ){
        composable(Screens.GameScreen.route) {

        }
    }
}