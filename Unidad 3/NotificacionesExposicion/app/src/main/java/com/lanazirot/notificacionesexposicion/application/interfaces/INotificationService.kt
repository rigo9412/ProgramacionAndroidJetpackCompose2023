package com.lanazirot.notificacionesexposicion.application.interfaces

interface INotificationService {
    fun sendNotification(
        title: String = "Random number",
        message: String = "Your random number is: ${(0..100).random()}",
    )

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "random_number"
        const val NOTIFICATION_CHANNEL_NAME = "Random Number"
        const val NOTIFICATION_ID = 1
    }
}