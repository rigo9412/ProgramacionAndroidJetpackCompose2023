package com.tec.pokedexapp.ui.navigator.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tec.pokedexapp.ui.navigator.main.HomeScreenContainer
import com.tec.pokedexapp.ui.navigator.screens.Graphs

@Composable
fun RootGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Graphs.HomeGraph.route
    ){

        composable(route = Graphs.HomeGraph.route){
            HomeScreenContainer()
        }

        GameGraph(navController)
    }

}