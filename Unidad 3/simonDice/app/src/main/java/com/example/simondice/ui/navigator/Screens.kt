package com.example.simondice.ui.navigator

sealed class Screens(val route: String){
    object MainScreen: Screens("MainScreen")
    object GameScreen: Screens("GameScreen")
    object ResultScreen: Screens("ResultScreen")
}