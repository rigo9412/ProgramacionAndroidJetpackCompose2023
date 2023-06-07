package com.almy.gato

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.almy.gato.ui.theme.GatoTheme
import com.almy.memorama.SumaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hola: SumaViewModel by viewModels()

            GatoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    JuegoSumaScreen(
                        SumaViewModel(
                        context = applicationContext,
                            dataStoreManager = DataStoreManager(applicationContext)
                    )
                    )
                }
            }
        }
    }
}
