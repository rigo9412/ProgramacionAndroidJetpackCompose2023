package com.tec.pokedexapp.ui.navigator.main

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tec.pokedexapp.ui.components.BottomBar
import com.tec.pokedexapp.ui.navigator.graphs.HomeGraph
import com.tec.pokedexapp.ui.navigator.graphs.RootGraph
import com.tec.pokedexapp.ui.navigator.screens.Graphs
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
    Text(text = "HOME")
}