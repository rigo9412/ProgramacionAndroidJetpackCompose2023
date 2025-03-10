package com.rigo.simondice.ui.navigator

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rigo.simondice.MainScreen

@Composable
fun Navigator(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screens.MainScreen.route,
    ) {
        composable(Screens.MainScreen.route) {
            MainScreen()
        }
        composable(Screens.GameScreen.route) {
            Text(text = "SCORE")
        }
        composable(Screens.ResultScreen.route) {
            Text(text = "SCORE")
        }
    }
}