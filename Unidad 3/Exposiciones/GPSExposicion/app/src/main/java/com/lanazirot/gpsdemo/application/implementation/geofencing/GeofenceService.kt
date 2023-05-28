package com.lanazirot.gpsdemo.application.implementation.geofencing

import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.maps.model.LatLng
import com.lanazirot.gpsdemo.domain.interfaces.geofencing.IGeofenceService
import com.lanazirot.gpsdemo.ui.broadcast.GeofenceBroadcastReceiver


class GeofenceService(base: Context?) : ContextWrapper(base), IGeofenceService {

    private var pendingIntent: PendingIntent? = null

    override fun getGeofencingRequest(geofence: Geofence): GeofencingRequest {
        return GeofencingRequest.Builder().addGeofence(geofence)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER).build();
    }

    override fun getGeoFence(
        id: String, latLng: LatLng, radius: Float, transitionTypes: Int,
    ): Geofence {
        return Geofence.Builder()
            .setCircularRegion(latLng.latitude, latLng.longitude, radius)
            .setRequestId(id).setTransitionTypes(transitionTypes).setLoiteringDelay(5000)
            .setExpirationDuration(Geofence.NEVER_EXPIRE).build()
    }

    override fun getGeofencePendingIntent(): PendingIntent {
        if (pendingIntent != null) return pendingIntent as PendingIntent
        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 2607, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent as PendingIntent!!
    }

    override fun getErrorString(e: Exception): String {
        if (e is ApiException) {
            when (e.statusCode) {
                GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> return "GEOFENCE_NOT_AVAILABLE"
                GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> return "GEOFENCE_TOO_MANY_GEOFENCES"
                GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> return "GEOFENCE_TOO_MANY_PENDING_INTENTS"
            }
        }
        return e.localizedMessage
    }

}