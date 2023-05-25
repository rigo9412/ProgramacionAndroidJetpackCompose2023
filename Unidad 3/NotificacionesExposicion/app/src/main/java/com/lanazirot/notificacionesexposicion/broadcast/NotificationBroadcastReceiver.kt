package com.lanazirot.notificacionesexposicion.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.lanazirot.notificacionesexposicion.application.implementations.NotificationService
import com.lanazirot.notificacionesexposicion.application.interfaces.INotificationService

class NotificationBroadcastReceiver  : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationService: INotificationService = NotificationService(context)
        val randomNumber = (0..100).random()
        notificationService.sendNotification(message = "Your random number is: $randomNumber")
    }
}