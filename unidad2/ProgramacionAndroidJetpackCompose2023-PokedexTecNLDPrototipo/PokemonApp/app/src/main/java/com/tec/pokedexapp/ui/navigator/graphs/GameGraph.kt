package com.tec.pokedexapp.ui.navigator.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.tec.pokedexapp.ui.game.GameScreen
import com.tec.pokedexapp.ui.game.ResultScreen
import com.tec.pokedexapp.ui.navigator.screens.Graphs
import com.tec.pokedexapp.ui.navigator.screens.Screens

fun NavGraphBuilder.GameGraph(navController: NavHostController){
    navigation(
        startDestination = Screens.GameScreen.route,
        route = Graphs.GameGraph.route
    ){
        composable(route = Screens.GameScreen.route){
            GameScreen(navController = navController)
        }
        
        composable(
            route = Screens.ResultScreen.route,
            arguments = listOf(navArgument("score"){
                type = NavType.IntType
            })
        ){
            ResultScreen(navController = navController)
        }
    }

}