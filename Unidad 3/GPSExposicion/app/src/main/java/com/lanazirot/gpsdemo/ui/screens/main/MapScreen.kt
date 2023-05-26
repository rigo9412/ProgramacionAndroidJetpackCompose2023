package com.lanazirot.gpsdemo.ui.screens.main

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.lanazirot.gpsdemo.domain.interfaces.geofencing.IGeofenceService.Companion.GEOFENCE_RADIUS_IN_METERS
import com.lanazirot.gpsdemo.ui.permissions.LaunchMap

@Composable
fun BoxScope.WaitForMap(
    isMapLoaded: Boolean
) {
    AnimatedVisibility(
        modifier = Modifier
            .matchParentSize(),
        visible = !isMapLoaded,
        enter = EnterTransition.None,
        exit = fadeOut()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .wrapContentSize()
        )
    }
}



@Composable
fun MapScreen() {

    LaunchMap {
        val context = LocalContext.current
        val mapScreenViewModel: MapScreenViewModel = hiltViewModel()
        val markers = mapScreenViewModel.mapScreenState.collectAsState().value.markers

        var isMapLoaded by remember { mutableStateOf(false) }
        var uiSettings by remember { mutableStateOf(MapUiSettings()) }
        val properties by remember {
            mutableStateOf(
                MapProperties(
                    mapType = MapType.HYBRID,
                    isMyLocationEnabled = true
                )
            )
        }

        LaunchedEffect(Unit) {
            mapScreenViewModel.startLocationServices()
        }

        Box(Modifier.fillMaxSize()) {
            WaitForMap(isMapLoaded)
            GoogleMap(
                modifier = Modifier.matchParentSize(),
                properties = properties,
                uiSettings = uiSettings,
                onMapLoaded = {
                    isMapLoaded = true
                },
                onMyLocationClick = {
                    Toast.makeText(
                        context,
                        "My location clicked coordinates: ${it.latitude}, ${it.longitude}",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onMapLongClick = {
                    mapScreenViewModel.addMarker(MarkerState(position = it))
                }
            ) {
                markers.forEach { markerState ->
                    Marker(
                        state = markerState,
                        title = "Marker located at ${markerState.position.latitude}, ${markerState.position.longitude}",
                        onInfoWindowLongClick = {
                            mapScreenViewModel.deleteMarker(markerState)
                        }
                    )
                    Circle(center = markerState.position, radius = GEOFENCE_RADIUS_IN_METERS.toDouble(), fillColor = Color.Red, strokeColor = Color.Black, strokeWidth = 2.0f)
                }
            }
            Switch(
                checked = uiSettings.zoomControlsEnabled,
                onCheckedChange = {
                    uiSettings = uiSettings.copy(zoomControlsEnabled = it)
                }
            )
        }
    }
}