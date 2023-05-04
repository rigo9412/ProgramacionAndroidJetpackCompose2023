package com.example.simondice.ui.game

import com.example.simondice.domain.models.getresponsetop.Action


sealed class GameEvent {
    object StartGame: GameEvent()
    data class PressButtonEvent(val type: Action) : GameEvent()
}