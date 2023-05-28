package com.lanazirot.gpsdemo.domain.interfaces.notifications

interface INotificationService {
    fun sendNotification(
        title: String = "GPS Demo",
        message: String = "",
    )

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "gps_demo_channel"
        const val NOTIFICATION_CHANNEL_NAME = "GPS Demo"
        const val NOTIFICATION_ID = 1
    }
}