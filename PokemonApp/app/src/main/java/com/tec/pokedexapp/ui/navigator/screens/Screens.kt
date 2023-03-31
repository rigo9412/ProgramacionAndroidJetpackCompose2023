package com.tec.pokedexapp.ui.navigator.screens

sealed class Screens(val route: String){
    object HomeScreen: Screens("HomeScreen")
    object PerfilScreen: Screens("PerfilScreen")
    object GameScreen: Screens("GameScreen")
    object ResultScreen: Screens("ResultScreen/{score}")
    object PokemonListScreen: Screens("PokemonListScreen")
    object PokemonScreen: Screens("PokemonScreen/{id}")
}

sealed class Graphs(val route: String){
    object RootGraph: Graphs("RootGraph")
    object HomeGraph: Graphs("HomeGraph")
    object GameGraph: Graphs("GameGraph")
    object PokeGraph: Graphs("PokeGraph")
}