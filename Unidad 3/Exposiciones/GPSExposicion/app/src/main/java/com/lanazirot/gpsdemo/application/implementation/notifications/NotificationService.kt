package com.lanazirot.gpsdemo.application.implementation.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.lanazirot.gpsdemo.domain.interfaces.notifications.INotificationService
import com.lanazirot.gpsdemo.domain.interfaces.notifications.INotificationService.Companion.NOTIFICATION_CHANNEL_ID
import javax.inject.Inject


class NotificationService @Inject constructor(private val context: Context) : INotificationService {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun sendNotification(title: String, message: String) {
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}