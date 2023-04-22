package com.tec.pokedexapp.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens
import com.tec.pokedexapp.ui.navigator.screens.Screens

@Composable
fun ResultScreen(navController: NavHostController){
    Column() {
        Text("RESULT")
        Button(onClick = {
            navController.navigate(BottomBarScreens.Home.route){
                popUpTo(BottomBarScreens.Home.route){inclusive = true}
            }
        }){
            Text("Regresar")
        }
    }
}