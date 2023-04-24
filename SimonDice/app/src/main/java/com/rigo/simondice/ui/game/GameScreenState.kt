package com.rigo.simondice.ui.game

import com.rigo.simondice.domain.models.Player

sealed class GameScreenState {
    object Init : GameScreenState()
    object Ended : GameScreenState()
    object Started : GameScreenState()
    class EndedToTop(val player: Player) : GameScreenState()
}