package com.jordan.simondice.ui.top

import com.jordan.simondice.domain.models.Player

sealed class TopScreenState {

    class Ready(val top: List<Player>): TopScreenState()
    class Error(val message: String): TopScreenState()
    object Loading: TopScreenState()
}