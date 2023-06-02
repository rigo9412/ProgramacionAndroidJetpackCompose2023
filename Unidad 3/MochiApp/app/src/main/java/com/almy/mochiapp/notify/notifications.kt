package com.almy.mochiapp.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.almy.mochiapp.MainActivity
import com.almy.mochiapp.R

fun provideNotificationBuilder(
    context: Context,
    title: String,
    content: String
): NotificationCompat.Builder {
    val flag =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_IMMUTABLE
        else
            0

    //intent para abrir la app en la pantalla que quedó en ejecución
    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, flag)


    return NotificationCompat.Builder(context, "Main Channel ID")
        .setContentTitle(title)
        .setContentText(content)
        .setSmallIcon(R.drawable.crarnota)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)
}

fun provideNotificationManager(
    context: Context
): NotificationManagerCompat {
    val notificationManager = NotificationManagerCompat.from(context)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "Main Channel ID",
            "Assigments Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }
    return notificationManager
}