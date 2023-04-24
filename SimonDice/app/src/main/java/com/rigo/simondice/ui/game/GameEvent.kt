package com.rigo.simondice.ui.game

import android.media.MediaPlayer
import com.rigo.simondice.domain.models.Action

sealed class GameEvent {
    object StartGame: GameEvent()
    data class PressButtonEvent(val type: Action) : GameEvent()
}