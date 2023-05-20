package com.almy.simondice

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.almy.simondice.domain.models.Player
import com.almy.simondice.ui.theme.SimonDiceTheme
import com.almy.simondice.ui.topstate.NotificationState
import com.almy.simondice.ui.topstate.TopScreen
import com.almy.simondice.ui.topstate.TopViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val simonDiceViewModel: SimonDiceViewModel by viewModels()
            val viewModel2: TopViewModel by viewModels()

            createNotificationChannel(LocalContext.current)
            RequestPermissionNotification(LocalContext.current)
          //  NotificationListener()

            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Nuestra pantalla de simon DIce
                    //GatoScreen(simonDiceViewModel)

                    //GameScreen(viewModel = viewModel, viewModel2 = viewModel2)
                    viewModel2.postTopFake(Player(null," pruebaM",78,1))
                    TopScreen(viewModel = viewModel2)
                }
            }
        }
    }
}

private fun createNotificationChannel(context: Context) {
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
private fun NotificationListener(topViewModel: TopViewModel){
    val topViewModel = hiltViewModel<TopViewModel>()
    val stateM = topViewModel.notificationState.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(stateM) {
        if(stateM is NotificationState.ShowNotification){
            val builder = NotificationCompat.Builder(context, "TOP")
                //.setSmallIcon(R.drawable.ic_top)
                .setContentTitle("NUEVO TOP")
                .setChannelId("TOP")
                .setContentText("El jugador ${stateM.player.name} obtuvo un score de ${stateM.player.score}")
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
private fun RequestPermissionNotification(context: Context) {
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



