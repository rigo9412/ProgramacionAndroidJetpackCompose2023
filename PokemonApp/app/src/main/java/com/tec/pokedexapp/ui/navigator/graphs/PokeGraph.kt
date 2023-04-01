package com.tec.pokedexapp.ui.navigator.graphs

import android.content.res.AssetManager
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.screens.Graphs
import com.tec.pokedexapp.ui.navigator.screens.Screens
import com.tec.pokedexapp.ui.pokemon.PokemonListScreen
import com.tec.pokedexapp.ui.pokemon.PokemonScreen

fun NavGraphBuilder.PokeGraph(navController: NavHostController, globalProvider: GlobalProvider){
    navigation(
        startDestination = Screens.PokemonListScreen.route,
        route = Graphs.PokeGraph.route
    ){
        composable(route = Screens.PokemonListScreen.route){
            PokemonListScreen(navController,globalProvider)
        }

        composable(
            route = Screens.PokemonScreen.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){
            PokemonScreen(navController = navController)
        }
    }
}