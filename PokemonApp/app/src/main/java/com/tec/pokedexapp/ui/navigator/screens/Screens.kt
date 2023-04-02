package com.tec.pokedexapp.ui.navigator.screens

import com.tec.pokedexapp.ui.game.GameState

const val ARGUMENT_SCORE = "score"
const val ARGUMENT_POKEMONID = "id"
const val ARGUMENT_STATE = "state"
sealed class Screens(val route: String){
    object HomeScreen: Screens("HomeScreen")
    object PerfilScreen: Screens("PerfilScreen")
    object GameScreen: Screens("GameScreen")
    object ResultScreen: Screens("ResultScreen/{$ARGUMENT_SCORE}/{$ARGUMENT_STATE}"){
        fun passScoreAndState(score : Int,gameState: String): String{
            return "ResultScreen/$score/${gameState}"
        }
    }
    object PokemonListScreen: Screens("PokemonListScreen")
    object PokemonScreen: Screens("PokemonScreen/{$ARGUMENT_POKEMONID}"){
        fun passId(id: Int): String{
            return "PokemonScreen/$id"
        }
    }
}

sealed class Graphs(val route: String){
    object RootGraph: Graphs("RootGraph")
    object HomeGraph: Graphs("HomeGraph")
    object GameGraph: Graphs("GameGraph")
    object PokeGraph: Graphs("PokeGraph")
}