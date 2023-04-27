package com.rigo.simondice

import CustomButton
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rigo.simondice.domain.models.Player
import com.rigo.simondice.ui.components.TitleShadow
import com.rigo.simondice.ui.navigator.Navigator
import com.rigo.simondice.ui.theme.*
import com.rigo.simondice.ui.top.TopScreen
import com.rigo.simondice.ui.top.TopViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.rigo.simondice.ui.top.NotificationState

val LocalNav = compositionLocalOf<NavHostController> { error("No navigation host controller provided.") }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            createNotificationChannel(LocalContext.current)
            RequestPermissionNotification(LocalContext.current)
            NotificationListener()
            SimonDiceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CompositionLocalProvider(LocalNav provides navController) {
                        Navigator(navController = navController)
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen(){
    val topViewModel = hiltViewModel<TopViewModel>()
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title,topList,startbutton) = createRefs()

        TitleShadow("SIMON GAME", modifier =  Modifier.constrainAs(title) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        TopScreen(modifier =  Modifier.constrainAs(topList) {
            top.linkTo(title.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(startbutton.top)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })

        CustomButton("INICIAR JUEGO", modifier = Modifier.constrainAs(startbutton) {
            bottom.linkTo(parent.bottom)
        },false){
            topViewModel.postTopFake(Player(null,"dummy",10,1))
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
private fun NotificationListener(){
    val topViewModel = hiltViewModel<TopViewModel>()
    val stateM = topViewModel.notificationState.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(stateM) {
        if(stateM is NotificationState.ShowNotification){
            val builder = NotificationCompat.Builder(context, "TOP")
                .setSmallIcon(R.drawable.ic_top)
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


@Preview
@Composable
fun MainPreview(){
    MainScreen()
}







