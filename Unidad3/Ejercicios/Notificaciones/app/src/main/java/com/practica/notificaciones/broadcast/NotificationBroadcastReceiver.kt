package com.practica.notificaciones.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.practica.notificaciones.notification.INotificationService
import com.practica.notificaciones.notification.NotificationService

class NotificationBroadcastReceiver  : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationService: INotificationService = NotificationService(context)
        val randomNumber = (0..753).random()
        notificationService.sendNotification(message = "Welcome master, there are $randomNumber troops in our army. Ready to fight?", title = "Army status")
    }
}