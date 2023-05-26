package com.practica.notificaciones.notification


interface INotificationService {
    fun sendNotification(
        title: String = "Army status",
        message: String = "Welcome master, there are ${(0..753).random()} troops in our army. Ready to fight?",
    )

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "random_number"
        const val NOTIFICATION_CHANNEL_NAME = "Random Number"
        const val NOTIFICATION_ID = 1
    }
}