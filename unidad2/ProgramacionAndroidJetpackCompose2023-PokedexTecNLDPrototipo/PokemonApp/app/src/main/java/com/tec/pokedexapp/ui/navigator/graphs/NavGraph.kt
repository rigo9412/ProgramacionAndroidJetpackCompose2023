package com.tec.pokedexapp.ui.navigator.graphs

import android.content.res.AssetManager
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tec.pokedexapp.data.source.Pokemon
import com.tec.pokedexapp.ui.navigator.main.HomeScreenContainer
import com.tec.pokedexapp.ui.navigator.screens.Graphs

@Composable
fun RootGraph(navController: NavHostController,assetManager: AssetManager, pokemonList: List<Pokemon>){
    NavHost(
        navController = navController,
        startDestination = Graphs.HomeGraph.route
    ){

        composable(route = Graphs.HomeGraph.route){
            HomeScreenContainer(assetManager = assetManager, pokemonList = pokemonList)
        }
    }

}