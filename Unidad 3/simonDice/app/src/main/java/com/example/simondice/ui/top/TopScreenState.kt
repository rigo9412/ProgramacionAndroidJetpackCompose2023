package com.example.simondice.ui.top

import com.example.simondice.domain.models.Player

sealed class TopScreenState {

    class Ready(val top: List<Player>): TopScreenState()
    class Error(val message: String): TopScreenState()
    object Loading: TopScreenState()
}