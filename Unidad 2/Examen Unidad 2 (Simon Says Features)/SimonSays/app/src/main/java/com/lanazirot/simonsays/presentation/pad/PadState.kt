package com.lanazirot.simonsays.presentation.pad

import com.lanazirot.simonsays.domain.model.Pad
import com.lanazirot.simonsays.domain.model.Player

data class PadState(
    val currentStep: Int = 0,
    val pad : Pad? = null,
    val player: Player? = null,
    val gameStatus: GameStatus = GameStatus.HOLD,
    val isGoingToScoreboard: Boolean = false
)

enum class GameStatus {
    START,
    PLAYING,
    GAME_OVER,
    HOLD,
    PAD_YELLING
}