package com.tec.pokedexapp.ui.navigator.graphs

import android.content.res.AssetManager
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.main.HomeScreenContainer
import com.tec.pokedexapp.ui.navigator.screens.Graphs

@Composable
fun RootGraph(globalProvider: GlobalProvider){
    NavHost(
        navController = globalProvider.nav,
        startDestination = Graphs.HomeGraph.route
    ){
        composable(route = Graphs.HomeGraph.route){
            HomeScreenContainer(globalProvider = globalProvider)
        }
    }

}