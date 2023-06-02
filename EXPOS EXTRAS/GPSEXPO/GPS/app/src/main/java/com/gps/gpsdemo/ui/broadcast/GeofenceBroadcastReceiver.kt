package com.gps.gpsdemo.ui.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.gps.gpsdemo.application.implementation.notifications.NotificationService
import com.gps.gpsdemo.domain.interfaces.notifications.INotificationService


class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent!!)
        val notificationService: INotificationService = NotificationService(context!!)
        when {
            geofencingEvent!!.hasError() -> {
                Log.e("GeofenceBroadcast", geofencingEvent.errorCode.toString())
                notificationService.sendNotification(message = "GeofenceBroadcast error: ${geofencingEvent.errorCode}")
                return
            }
            else -> when (geofencingEvent!!.geofenceTransition) {
                Geofence.GEOFENCE_TRANSITION_ENTER -> {
                    notificationService.sendNotification(message = "GEOFENCE_TRANSITION_ENTER")
                }

                Geofence.GEOFENCE_TRANSITION_DWELL -> {
                    notificationService.sendNotification(message = "GEOFENCE_TRANSITION_DWELL")
                }

                Geofence.GEOFENCE_TRANSITION_EXIT -> {
                    notificationService.sendNotification(message = "GEOFENCE_TRANSITION_EXIT")
                }
            }
        }

    }
}