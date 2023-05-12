package com.example.simondice.domain.game

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simondice.R
import com.example.simondice.models.NotificationState
import com.example.simondice.models.TopViewModel

fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    val CHANNEL_ID = "TOP"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Create the NotificationChannel.
        val name = "CHANEL TOP"
        val descriptionText = "TOP DESCRIPTION"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system. You can't change the importance
        // or other notification behaviors after this.
        with(NotificationManagerCompat.from(context)) {
            this.createNotificationChannel(mChannel)
        }

    }

}

@Composable
fun NotificationListener(){
    val topViewModel = hiltViewModel<TopViewModel>()
    val stateM = topViewModel.notificationState.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(stateM) {
        if(stateM is NotificationState.ShowNotification){
            val builder = NotificationCompat.Builder(context, "TOP")
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("¡Nueva puntuación encontrada!")
                .setChannelId("TOP")
                .setContentText("¡El jugador ${stateM.player.name} obtuvo ${stateM.player.score} puntos!")
                .setPriority(NotificationCompat.DEFAULT_ALL)

            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(10, builder.build())
                }

            }

        }
    }
}

@Composable
fun RequestPermissionNotification(context: Context) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // permission granted
        } else {
            // permission denied, but should I show a rationale?
        }
    }

    val context = LocalContext.current
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        // permission granted
    } else {
        SideEffect {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

    }
}