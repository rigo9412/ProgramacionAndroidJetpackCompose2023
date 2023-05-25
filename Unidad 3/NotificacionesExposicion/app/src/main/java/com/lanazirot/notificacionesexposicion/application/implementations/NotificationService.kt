package com.lanazirot.notificacionesexposicion.application.implementations

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.lanazirot.notificacionesexposicion.MainActivity
import com.lanazirot.notificacionesexposicion.R
import com.lanazirot.notificacionesexposicion.application.interfaces.INotificationService
import com.lanazirot.notificacionesexposicion.application.interfaces.INotificationService.Companion.NOTIFICATION_CHANNEL_ID
import com.lanazirot.notificacionesexposicion.broadcast.NotificationBroadcastReceiver
import javax.inject.Inject


class NotificationService @Inject constructor(private val context: Context) : INotificationService {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun sendNotification(title: String, message: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val incrementIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, NotificationBroadcastReceiver::class.java),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.ic_launcher_background,
                "Get another number",
                incrementIntent
            )
            .build()

        notificationManager.notify(1, notification)
    }
}