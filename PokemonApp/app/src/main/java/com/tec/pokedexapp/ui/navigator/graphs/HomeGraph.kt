package com.tec.pokedexapp.ui.navigator.graphs

import android.content.res.AssetManager
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.main.HomeScreen
import com.tec.pokedexapp.ui.navigator.main.PerfilScreen
import com.tec.pokedexapp.ui.navigator.main.leaderboard.LeaderboardScreen
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens
import com.tec.pokedexapp.ui.navigator.screens.Graphs
import com.tec.pokedexapp.ui.navigator.screens.Screens
import com.tec.pokedexapp.ui.pokemon.PokemonListScreen

@Composable
fun HomeGraph(navController: NavHostController,globalProvider: GlobalProvider){
    NavHost(
        navController = navController,
        route = Graphs.HomeGraph.route,
        startDestination = BottomBarScreens.Home.route
    ){
        composable(route = BottomBarScreens.Home.route){
            HomeScreen(navController = navController,globalProvider)
        }

        composable(route = BottomBarScreens.Perfil.route){
            PerfilScreen(navController = navController,globalProvider)
        }

        composable(route = BottomBarScreens.Pokedex.route){
            PokemonListScreen(navController, globalProvider)
        }

        composable(route = BottomBarScreens.LeaderBoard.route){
            LeaderboardScreen(navController, globalProvider)
        }


        PokeGraph(navController,globalProvider)
        GameGraph(navController,globalProvider)
    }
}