package com.gps.gpsdemo.ui.permissions

import android.Manifest.*
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LaunchMap(
    content: @Composable () -> Unit,
) {
    val permissions = arrayOf(
        permission.ACCESS_FINE_LOCATION,
        permission.ACCESS_BACKGROUND_LOCATION,
        permission.POST_NOTIFICATIONS
    )


    /*
    * Friendly reminder: A partir de Android 11, si el usuario pulsa Denegar
    * tendra que colocar los permisos el manualmente en los settings de la App
    *
    * https://developer.android.com/about/versions/11/privacy/permissions
    *
    * */

    content()
}