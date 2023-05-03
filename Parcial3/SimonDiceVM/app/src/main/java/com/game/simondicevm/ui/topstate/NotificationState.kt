package com.game.simondicevm.ui.topstate

import com.rigo.simondice.domain.models.Player

sealed class NotificationState {
    class ShowNotification(val player: Player): NotificationState()
    object HideBotification: NotificationState()
}
