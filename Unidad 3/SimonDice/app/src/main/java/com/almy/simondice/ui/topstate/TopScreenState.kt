package com.almy.simondice.ui.topstate

import com.almy.simondice.domain.models.Player


sealed class TopScreenState {
    class Ready(val top: List<Player>): TopScreenState()
    class Error(val message: String): TopScreenState()
    object Loading: TopScreenState()
}