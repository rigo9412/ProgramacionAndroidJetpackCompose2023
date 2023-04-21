package com.lanazirot.simonsays.presentation.providers

import android.annotation.SuppressLint
import androidx.compose.runtime.compositionLocalOf
import com.lanazirot.simonsays.presentation.scoreboard.components.ScoreboardViewModel

data class GameProvider (
    val currentGame:ScoreboardViewModel
)

@SuppressLint("CompositionLocalNaming")
val GlobalGameProvider = compositionLocalOf<GameProvider> { error("No user provider provided.") }