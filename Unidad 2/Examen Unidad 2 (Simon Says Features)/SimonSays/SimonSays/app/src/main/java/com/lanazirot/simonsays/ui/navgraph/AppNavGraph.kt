package com.lanazirot.simonsays.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.lanazirot.simonsays.presentation.providers.GlobalProvider

@Composable
fun AppNavGraph(globalProvider: GlobalProvider) {
    val navController = globalProvider.nav
    NavHost(navController = navController, startDestination = "pad") {
        PadNavGraph()
        ScoreboardNavGraph()
    }
}