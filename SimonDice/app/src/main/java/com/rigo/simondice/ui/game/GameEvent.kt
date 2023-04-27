package com.rigo.simondice.ui.game

import com.rigo.simondice.domain.models.getresponsetop.Action

sealed class GameEvent {
    object StartGame: GameEvent()
    data class PressButtonEvent(val type: Action) : GameEvent()
}