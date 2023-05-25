package com.practica.notificaciones

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationManager: NotificationManagerCompat,
    private val notificationBuilder: NotificationCompat.Builder
) : ViewModel(){
    fun showSimpleNotification(){
        notificationManager.notify(1, notificationBuilder.build())
    }

    fun updateSimpleNotification(){
        notificationManager.notify(1,notificationBuilder.setContentText("Tiempo transcurrido:").build())
    }
    fun cancelSimpleNotification(){
        notificationManager.cancel(1)
    }
}