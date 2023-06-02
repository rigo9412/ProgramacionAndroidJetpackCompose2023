package com.almy.mochiapp.screens.Account

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import com.almy.mochiapp.screens.CreateTask.ScreenUiState
import com.almy.mochiapp.screens.MainSreen.LoadingScreen

@Composable
fun OnAccountScreen(
    viewModel: AccountViewModel
)
{
    val state = viewModel.uiState.collectAsState().value
    
    when(state)
    {
        is ScreenUiState.Loading -> { LoadingScreen() }
        is ScreenUiState.Ready -> { AccountScreen(viewModel = viewModel) }
        else -> {  }
    }
}

@Composable
fun AccountScreen(
    viewModel: AccountViewModel
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        Text(text = "Nombre de usuario: ")
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Email: ")
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Amigos: ")
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Tareas: ")
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

