package com.example.simondice.ui.game

import com.example.simondice.domain.models.Player


sealed class GameScreenState {
    object Init : GameScreenState()
    object Ended : GameScreenState()
    object Started : GameScreenState()
    class EndedToTop(val player: Player) : GameScreenState()
}