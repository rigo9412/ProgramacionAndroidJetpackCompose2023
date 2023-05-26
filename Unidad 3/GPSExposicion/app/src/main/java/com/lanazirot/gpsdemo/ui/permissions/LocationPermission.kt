package com.lanazirot.gpsdemo.ui.permissions

import android.Manifest
import android.Manifest.permission
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.hilt.navigation.compose.hiltViewModel


/*
* Friendly reminder: A partir de Android 11, si el usuario pulsa Denegar
* tendra que colocar los permisos el manualmente en los settings de la App
*
* https://developer.android.com/about/versions/11/privacy/permissions
*
* */

private val permissions = arrayOf(
    permission.ACCESS_FINE_LOCATION,
    permission.ACCESS_BACKGROUND_LOCATION,
    permission.ACCESS_NOTIFICATION_POLICY
)


@Composable
fun LaunchMap(
    content: @Composable () -> Unit,
) {
    val locationPermissionViewModel: LocationPermissionViewModel = hiltViewModel()
    val dialogQueue = locationPermissionViewModel.visiblePermissionDialogQueue
    val context = LocalContext.current

    val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            permissions.forEach { permission ->
                locationPermissionViewModel.onPermissionResult(
                    permission = permission,
                    isGranted = perms[permission] == true
                )
            }
        }
    )

    LaunchedEffect(Unit){
        multiplePermissionResultLauncher.launch(permissions)
    }

    dialogQueue
        .reversed()
        .forEach { permission ->
            PermissionDialog(
                permissionTextProvider = when (permission) {
                    Manifest.permission.POST_NOTIFICATIONS -> {
                        NotificationPermission()
                    }

                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        LocationPermission()
                    }

                    Manifest.permission.ACCESS_BACKGROUND_LOCATION -> {
                        BackgroundLocationPermission()
                    }

                    else -> return@forEach
                },
                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                    LocalContext.current as Activity,
                    permission
                ),
                onDismiss = locationPermissionViewModel::dismissDialog,
                onOkClick = {
                    locationPermissionViewModel.dismissDialog()
                    multiplePermissionResultLauncher.launch(
                        arrayOf(permission)
                    )
                },
                onSettingsClick = {
                    openSettings(context as Activity)
                }
            )
        }

    content()
}

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Permission required")
        },
        confirmButton = {
            Text(
                text = if (isPermanentlyDeclined) {
                    "Grant permission"
                } else {
                    "OK"
                },
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (isPermanentlyDeclined) {
                            onSettingsClick()
                        } else {
                            onOkClick()
                        }
                    }
                    .padding(16.dp)
            )
        },
        text = {
            Text(
                text = permissionTextProvider.getDescription(
                    isPermanentlyDeclined = isPermanentlyDeclined
                )
            )
        },
        modifier = modifier
    )
}

fun openSettings(context: Activity) {
    context.startActivity(android.content.Intent().apply {
        action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = android.net.Uri.fromParts("package", context.packageName, null)
    })
}
