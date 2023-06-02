package com.gps.gpsdemo.domain.interfaces.geofencing

import android.app.PendingIntent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.maps.model.LatLng

interface IGeofenceService {
    fun getGeofencingRequest(geofence: Geofence): GeofencingRequest
    fun getGeoFence(id: String, latLng: LatLng, radius: Float, transitionTypes: Int): Geofence
    fun getGeofencePendingIntent(): PendingIntent
    fun getErrorString(e: Exception): String

    companion object{
        const val GEOFENCE_RADIUS_IN_METERS = 100f
    }
}