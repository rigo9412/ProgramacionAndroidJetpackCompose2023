package com.lanazirot.simonsays.ui.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.simonsays.presentation.scoreboard.components.ScoreBoardScreen

fun NavGraphBuilder.ScoreboardNavGraph() {
    composable(
        route = "scoreboard"
    ) {
        ScoreBoardScreen()
    }
}