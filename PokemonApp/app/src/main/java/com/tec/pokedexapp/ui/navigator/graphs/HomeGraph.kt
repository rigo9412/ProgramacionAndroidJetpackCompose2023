package com.tec.pokedexapp.ui.navigator.graphs

import android.content.res.AssetManager
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tec.pokedexapp.data.source.Pokemon
import com.tec.pokedexapp.ui.game.GameScreen
import com.tec.pokedexapp.ui.navigator.main.HomeScreen
import com.tec.pokedexapp.ui.navigator.main.PerfilScreen
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens
import com.tec.pokedexapp.ui.navigator.screens.Graphs
import com.tec.pokedexapp.ui.navigator.screens.Screens
import com.tec.pokedexapp.ui.pokemon.PokemonListScreen

@Composable
fun HomeGraph(navController: NavHostController,assetManager: AssetManager,pokemonList : List<Pokemon>){
    NavHost(
        navController = navController,
        route = Graphs.HomeGraph.route,
        startDestination = BottomBarScreens.Home.route
    ){
        composable(route = BottomBarScreens.Home.route){
            HomeScreen(navController = navController)
        }

        composable(route = BottomBarScreens.Perfil.route){
            PerfilScreen(navController = navController)
        }

        composable(route = BottomBarScreens.Pokedex.route){
            PokemonListScreen(pokemonList, navController,assetManager)
        }
        
        GameGraph(navController)
        PokeGraph(navController,pokemonList,assetManager)

        GameGraph(navController)
    }
}