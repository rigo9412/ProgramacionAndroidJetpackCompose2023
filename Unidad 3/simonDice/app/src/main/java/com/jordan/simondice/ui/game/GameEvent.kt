package com.jordan.simondice.ui.game

import com.jordan.simondice.domain.models.getresponsetop.Action

sealed class GameEvent {
    object StartGame: GameEvent()
    data class PressButtonEvent(val type: Action) : GameEvent()
}