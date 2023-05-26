package com.lanazirot.gpsdemo.ui.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.maps.android.compose.MarkerState
import com.lanazirot.gpsdemo.domain.interfaces.location.ILocationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val locationClient: ILocationClient
) : ViewModel() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _mapScreenState: MutableStateFlow<MapScreenState> = MutableStateFlow(MapScreenState())
    val mapScreenState = _mapScreenState

    //region Markers
    fun addMarker(markerState: MarkerState) {
        val markers = _mapScreenState.value.markers.toMutableList()
        markers.add(markerState)
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
        val fiveMinutes = 1500
        Log.d("LocationClient", "Starting location services")
        locationClient.getLocationUpdates(fiveMinutes.toLong()).catch {
            Log.e("LocationClient", "Error: ${it.message}")
        }.onEach {
            Log.d("LocationClient", "Location: ${it.latitude} ${it.longitude} - ${Date()}}")
        }.launchIn(serviceScope)
    }
    //endregion

}