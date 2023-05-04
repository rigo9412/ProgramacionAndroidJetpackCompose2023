package com.example.simondice.ui.top

import com.example.simondice.domain.models.Player

sealed class NotificationState {
    class ShowNotification(val player: Player): NotificationState()
    object HideBotification: NotificationState()
}