package com.tec.pokedexapp.ui.navigator.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.tec.pokedexapp.ui.game.GameScreen
import com.tec.pokedexapp.ui.game.ResultScreen
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.screens.Graphs
import com.tec.pokedexapp.ui.navigator.screens.Screens

fun NavGraphBuilder.GameGraph(navController: NavHostController,globalProvider: GlobalProvider){
    navigation(
        startDestination = Screens.GameScreen.route,
        route = Graphs.GameGraph.route
    ){
        composable(route = Screens.GameScreen.route){
            GameScreen(navController = navController,globalProvider) {
                globalProvider.gameVM.stopGame()
                navController.popBackStack()}
        }
        
        composable(
            route = Screens.ResultScreen.route,
            arguments = listOf(navArgument("score"){
                    type = NavType.IntType
                },
                navArgument("state"){
                    type = NavType.StringType
                }
            )
        ){
            ResultScreen(navController = navController,
                globalProvider = globalProvider,
                it.arguments?.getInt("score"),
                it.arguments?.getString("state"))
        }
    }

}