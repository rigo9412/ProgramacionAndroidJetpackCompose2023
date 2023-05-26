package com.example.notificationdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.example.notificationdemo.cronometro.Cronometro
import com.example.notificationdemo.screen.MainScreen
import com.example.notificationdemo.ui.theme.NotificationDemoTheme
import dagger.hilt.android.AndroidEntryPoint

var cronometro = Cronometro()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private fun requestPermissions(vararg permissions: String) {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            result.entries.forEach {
                Log.d("MainActivity", "${it.key} = ${it.value}")
            }
        }
        requestPermissionLauncher.launch(permissions.asList().toTypedArray())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //requestPermissions(Manifest.permission.POST_NOTIFICATION)
        super.onCreate(savedInstanceState)
        setContent {
            NotificationDemoTheme {
                MainScreen()
            }
        }
    }
}