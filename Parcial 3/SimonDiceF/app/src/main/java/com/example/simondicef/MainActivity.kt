package com.example.simondicef

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simondicef.data.UserStore
import com.example.simondicef.domain.models.User
import com.example.simondicef.leaderboard.ui.LeaderboardViewModel
import com.example.simondicef.leaderboard.ui.UserViewModel
import com.example.simondicef.ui.simondice.ui.SimonDiceViewModel
import com.example.simondicef.ui.simondice.ui.simonDiceScreen
import com.example.simondicef.ui.theme.SimonDiceFTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val store = UserStore(LocalContext.current)
            val darkmode = store.getDarkModeValue.collectAsState(initial = false).value
            val coroutineScope = rememberCoroutineScope()

            SimonDiceFTheme(darkTheme = darkmode) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = applicationContext
                    createNotificationChannel(context)
                    var userVM : UserViewModel = hiltViewModel()

                    simonDiceScreen(
                        viewModel = SimonDiceViewModel(),
                        leaderboard = LeaderboardViewModel(),
                        userViewModel =  userVM,
                        darkMode = darkmode, onDarkModeChange = {
                            coroutineScope.launch{
                                store.saveDarkModeValue(it)
                            }
                        })
                    //testVM()
                }
            }
        }
    }
}

private fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "SIMON DICE"
        val descriptionText = "SIMON DICE APP"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("TOP", name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}


//@Composable
//fun testVM(
//    viewmodel: UserViewModel = hiltViewModel()
//){
//    Column() {
//        Button(onClick = { viewmodel.getUsers() }) {
//            Text("CLICK ME")
//        }
//    }
//}