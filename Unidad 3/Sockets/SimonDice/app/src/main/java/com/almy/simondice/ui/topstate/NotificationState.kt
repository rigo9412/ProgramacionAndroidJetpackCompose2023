package com.almy.simondice.ui.topstate

import com.almy.simondice.domain.models.Player


sealed class NotificationState {
    class ShowNotification(val player: Player): NotificationState()
    object HideBotification: NotificationState()
}
