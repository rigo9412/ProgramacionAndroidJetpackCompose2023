package com.tec.pokedexapp.ui.navigator.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tec.pokedexapp.ui.components.BottomBar
import com.tec.pokedexapp.ui.navigator.graphs.HomeGraph
import com.tec.pokedexapp.ui.navigator.screens.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenContainer(navController: NavHostController = rememberNavController()){
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {
        HomeGraph(navController = navController)
    }
}

@Composable
fun HomeScreen(navController: NavHostController){
    Box(modifier = Modifier.fillMaxSize()){
        Column() {
            Text(text = "HOME")
            Button(onClick = {navController.navigate(Screens.GameScreen.route)}){
                Text("Jugar")
            }
        }
    }
}