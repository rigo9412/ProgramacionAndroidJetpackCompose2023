package com.jordan.simondice.ui.top

import com.jordan.simondice.domain.models.Player

sealed class NotificationState {
    class ShowNotification(val player: Player): NotificationState()
    object HideBotification: NotificationState()
}
