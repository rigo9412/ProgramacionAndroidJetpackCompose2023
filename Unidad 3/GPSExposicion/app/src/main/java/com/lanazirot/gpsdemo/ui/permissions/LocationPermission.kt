package com.lanazirot.gpsdemo.ui.permissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LaunchMap(
    content: @Composable () -> Unit
) {
    val locationPermissionStatus = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    /*
    * Friendly reminder: A partir de Android 11, si el usuario pulsa Denegar
    * tendra que colocar los permisos el manualmente en los settings de la App
    *
    * https://developer.android.com/about/versions/11/privacy/permissions
    *
    * */

    when (locationPermissionStatus.allPermissionsGranted) {
        true -> content()
        else -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val textToShow =
                    "Location permission required for this feature to be available. Please grant the permission"
                Text(textToShow, modifier = Modifier.padding(16.dp))
                Button(onClick = { locationPermissionStatus.launchMultiplePermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }
}