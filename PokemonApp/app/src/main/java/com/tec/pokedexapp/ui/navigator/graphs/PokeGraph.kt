package com.tec.pokedexapp.ui.navigator.graphs

import android.content.res.AssetManager
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.tec.pokedexapp.data.source.Pokemon
import com.tec.pokedexapp.ui.navigator.screens.Graphs
import com.tec.pokedexapp.ui.navigator.screens.Screens
import com.tec.pokedexapp.ui.pokemon.PokemonListScreen
import com.tec.pokedexapp.ui.pokemon.PokemonScreen

fun NavGraphBuilder.PokeGraph(navController: NavHostController, pokemonList : List<Pokemon>, assetManager: AssetManager){
    navigation(
        startDestination = Screens.PokemonListScreen.route,
        route = Graphs.PokeGraph.route
    ){
        composable(route = Screens.PokemonListScreen.route){
            PokemonListScreen(pokemonList,navController,assetManager)
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