package com.practica.notificaciones

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.practica.notificaciones.cronometro.Cronometro
import com.practica.notificaciones.screen.MainScreen
import com.practica.notificaciones.ui.theme.NotificacionesTheme
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
            NotificacionesTheme {
                MainScreen()
            }
        }
    }
}