package com.rigo9412.poketest.ui.navigator

sealed class Screens(val route: String){
    object MainScreen: Screens("MainScreen")
    object GameScreen: Screens("GameScreen")
    object ResultScreen: Screens("ResultScreen")
}
