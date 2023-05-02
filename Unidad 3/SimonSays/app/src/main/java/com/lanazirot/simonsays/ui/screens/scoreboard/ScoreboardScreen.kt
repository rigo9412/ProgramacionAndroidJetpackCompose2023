package com.lanazirot.simonsays.ui.screens.scoreboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.simonsays.domain.model.Score


@Composable
fun ScoreBoardScreen() {
    val scoreboardViewModel: ScoreboardViewModel = hiltViewModel()
    val state = scoreboardViewModel.scoreboardState.collectAsState()

    BoxWithConstraints {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Top 10 Scores",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }

                    if (state.value.scores.isEmpty()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "No hay scores por el momento.",
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.White
                            )
                        }
                    }
                }
                items(state.value.scores) { score ->
                    ScoreText(score)
                }
            }
        )
    }
}

@Composable
fun ScoreText(score: Score) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = score.name + " - " + score.score.toString() + " pts.",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}