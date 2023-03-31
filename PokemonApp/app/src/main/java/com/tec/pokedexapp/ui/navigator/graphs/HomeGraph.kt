package com.tec.pokedexapp.ui.navigator.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tec.pokedexapp.ui.navigator.main.HomeScreen
import com.tec.pokedexapp.ui.navigator.main.PerfilScreen
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens
import com.tec.pokedexapp.ui.navigator.screens.Graphs
import com.tec.pokedexapp.ui.navigator.screens.Screens
import com.tec.pokedexapp.ui.pokemon.PokemonListScreen

@Composable
fun HomeGraph(navController: NavHostController){
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
            PokemonListScreen(navController = navController)
        }
        
        GameGraph(navController)
        PokeGraph(navController)
    }
}