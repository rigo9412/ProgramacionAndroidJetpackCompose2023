package com.lanazirot.simonsays.ui.providers

import android.annotation.SuppressLint
import androidx.compose.runtime.compositionLocalOf
import com.lanazirot.simonsays.ui.screens.scoreboard.ScoreboardViewModel

data class GameProvider (
    val currentGame: ScoreboardViewModel
)

@SuppressLint("CompositionLocalNaming")
val GlobalGameProvider = compositionLocalOf<GameProvider> { error("No user provider provided.") }