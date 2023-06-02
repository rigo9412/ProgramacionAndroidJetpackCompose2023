package com.otop.SimonDice.ui.top

import com.otop.SimonDice.domain.models.Player


sealed class TopScreenState {

    class Ready(val top: List<Player>): TopScreenState()
    class Error(val message: String): TopScreenState()
    object Loading: TopScreenState()
}