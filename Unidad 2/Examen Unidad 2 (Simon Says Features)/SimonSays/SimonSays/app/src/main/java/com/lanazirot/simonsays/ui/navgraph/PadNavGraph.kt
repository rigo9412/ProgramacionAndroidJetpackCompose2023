package com.lanazirot.simonsays.ui.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.simonsays.SimonSayGame

fun NavGraphBuilder.PadNavGraph() {
    composable(
        route = "pad"
    ) {
        SimonSayGame()
    }
}