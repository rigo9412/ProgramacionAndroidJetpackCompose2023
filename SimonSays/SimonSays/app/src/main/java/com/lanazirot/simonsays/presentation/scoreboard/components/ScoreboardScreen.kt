package com.lanazirot.simonsays.presentation.scoreboard.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ScoreBoardScreen() {

    val scoreboardViewModel: ScoreboardViewModel = hiltViewModel()
    val scoreboardList = scoreboardViewModel.scoreboardList

    BoxWithConstraints {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(scoreboardList) { scoreObj ->
                    Row{
                       Text(text = "Fulanito de tal ${scoreObj}")
                    }
                }
            }
        )
    }
}
