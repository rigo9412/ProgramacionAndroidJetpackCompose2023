package com.tec.pokedexapp.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tec.pokedexapp.ui.navigator.screens.Screens

@Composable
fun GameScreen(navController: NavHostController?){
    Column() {
        Text("GAME")
        Button(onClick = { navController?.navigate(Screens.ResultScreen.passScore(10)) }) {
            Text("Ir a resultados")
        }
    }
}