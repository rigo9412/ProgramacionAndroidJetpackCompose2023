package com.otop.SimonDice.ui.top

import com.otop.SimonDice.domain.models.Player

sealed class NotificationState {
    class ShowNotification(val player: Player): NotificationState()
    object HideBotification: NotificationState()
}
