package com.program.nivelacion

import android.app.Application
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.program.nivelacion.ui.data.DataStoreManager
import com.program.nivelacion.ui.data.MainViewModel
import com.program.nivelacion.ui.pantallas.ControlDePantallas
import com.program.nivelacion.ui.theme.NivelacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = DataStoreManager(applicationContext)
        var mediaPlayer: MediaPlayer
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.sound1)
        setContent {
            NivelacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ControlDePantallas(MainViewModel(dataStore,
                        LocalContext.current.applicationContext as Application
                    ),mediaPlayer)
                }
            }
        }
    }
}
