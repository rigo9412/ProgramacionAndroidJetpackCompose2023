package com.lanazirot.simonsays.ui.screens.scoreboard

import com.lanazirot.simonsays.domain.model.Score

data class ScoreboardState(
    val isLoading: Boolean = false,
    val scores: List<Score> = emptyList(),
    val error: String = ""
)