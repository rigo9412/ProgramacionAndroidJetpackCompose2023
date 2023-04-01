package com.rigo9412.poketest.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rigo9412.poketest.ui.main.MainViewModel

@Composable
fun Navigator(navController: NavHostController,mainViewModel: MainViewModel) {
    NavHost(navController = navController,
        startDestination = RouterGraph.MAIN.toString(),
        route = RouterGraph.ROOT.toString()
    ) {
        GraphMain(mainViewModel)
        GraphGame()
    }
}