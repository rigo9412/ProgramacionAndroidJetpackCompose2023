package com.rigo9412.poketest.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen(vm: MainViewModel){
    val uiState by vm.mainUiState.collectAsState()
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)){

        when(uiState){
            is MainAppState.Done -> Text(text = "pokedex ${(uiState as MainAppState.Done).pokedex.count()}")
            MainAppState.Error -> Text(text = "error")
            MainAppState.Loading -> Text(text = "cargando")
        }        

    }
}