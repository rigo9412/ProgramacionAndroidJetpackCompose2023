package com.gps.gpsdemo.ui.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.maps.android.compose.MarkerState
import com.gps.gpsdemo.domain.interfaces.geofencing.IGeofenceService
import com.gps.gpsdemo.domain.interfaces.geofencing.IGeofenceService.Companion.GEOFENCE_RADIUS_IN_METERS
import com.gps.gpsdemo.domain.interfaces.location.ILocationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val locationClient: ILocationService,
    private val geofenceService: IGeofenceService,
    private val geofencingClient: GeofencingClient
) : ViewModel() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val _mapScreenState: MutableStateFlow<MapScreenState> =
        MutableStateFlow(MapScreenState())
    val mapScreenState = _mapScreenState

    //region Markers
    fun addMarker(markerState: MarkerState) {
        val markers = _mapScreenState.value.markers.toMutableList()
        markers.add(markerState)
        addGeofence(markerState)
        _mapScreenState.value = mapScreenState.value.copy(markers = markers)
    }

    fun deleteMarker(markerState: MarkerState): Boolean {
        val markers = _mapScreenState.value.markers.toMutableList()
        val result = markers.remove(markerState)
        _mapScreenState.value = mapScreenState.value.copy(markers = markers)
        return result
    }
    //endregion

    //region Location
    fun startLocationServices() {
       // Log.d("LocationClient", "Starting location services")
        /*locationClient.getLocationUpdates(1500).catch {
            Log.e("LocationClient", "Error: ${it.message}")
        }.onEach {
            Log.d("LocationClient", "Location: ${it.latitude} ${it.longitude} - ${Date()}}")
        }.launchIn(serviceScope)*/
    }
    //endregion

    //region Geofencing
    @SuppressLint("MissingPermission")
    private fun addGeofence(markerState: MarkerState) {
        val geofence = geofenceService.getGeoFence(
            "geofence-${markerState.hashCode()}",
            markerState.position,
            GEOFENCE_RADIUS_IN_METERS,
            Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_DWELL or Geofence.GEOFENCE_TRANSITION_EXIT
        )
        val geofencingRequest = geofenceService.getGeofencingRequest(geofence)
        val geofencePendingIntent = geofenceService.getGeofencePendingIntent()

        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
            .addOnSuccessListener {
                Log.d("Geofence", "Geofence added")
            }
            .addOnFailureListener {
                Log.e("Geofence", "Geofence not added because of error ... : ${geofenceService.getErrorString(it)}")
            }
    }
    //endregion
}