package com.otop.chinpokomon.data.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.otop.chinpokomon.data.MainViewModel
import com.otop.chinpokomon.data.Screen
import androidx.compose.ui.Modifier


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val currentScreen: Screen by viewModel.currentScreen.collectAsState()

    when (currentScreen) {
        is Screen.Home -> HomeScreen(viewModel)
        is Screen.Profile -> ProfileScreen(viewModel)
        is Screen.Settings -> SettingsScreen(viewModel)
    }
}


@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Green)) {
        Text(text = "Momento Verde")
    }
}

@Composable
fun ProfileScreen(viewModel: MainViewModel) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red)) {
        Text(text = "Momento Rojo")
    }
}

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Yellow)) {
        Text(text = "Momento Amarillo")
    }
}