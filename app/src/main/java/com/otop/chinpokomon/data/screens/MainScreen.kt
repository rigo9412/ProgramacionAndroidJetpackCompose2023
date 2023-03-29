package com.otop.chinpokomon.data.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.otop.chinpokomon.data.ViewModel
import java.lang.reflect.Modifier

@Composable
fun MainScreen(vm:ViewModel) {
    val uiState by vm.mainUiState.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)){
        when(uiState){
            is MainAppState.Done -> {
                LazyColumn(content = {
                    items((uiState as MainAppState.Done).pokedex){
                        Text(text = it.name)
                    }
                })
            }
            MainAppState.Error -> Text(text = "Error")
            MainAppState.Loading -> Text(text = "Loading")
        }
    }
}