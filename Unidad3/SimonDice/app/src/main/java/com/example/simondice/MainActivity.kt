package com.example.simondice

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.example.simondice.domain.models.*
import com.example.simondice.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import com.example.simondice.domain.game.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimonDiceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SimonGame(viewModel = FormViewModel())
                }
            }
        }
    }
}