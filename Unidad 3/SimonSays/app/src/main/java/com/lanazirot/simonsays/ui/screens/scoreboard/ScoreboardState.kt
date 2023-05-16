package com.lanazirot.simonsays.ui.screens.scoreboard

import com.lanazirot.simonsays.domain.model.Player

data class ScoreboardState(
    val isLoading: Boolean = false,
    val players: List<Player> = emptyList(),
    val error: String = ""
)