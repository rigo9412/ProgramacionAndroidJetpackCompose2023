package com.tec.pokedexapp.ui.game

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens

@Composable
fun ResultScreen(navController: NavHostController, globalProvider: GlobalProvider,score: Int?, state: String?){
    Column() {

        //GameState.END
        //GameState.LOST

        Text("RESULT")
        Text("Score: $score")
        Text("State: $state")
        Button(onClick = {
            navController.navigate(BottomBarScreens.Home.route){
                popUpTo(BottomBarScreens.Home.route){inclusive = true}
            }
        }){
            Text("Regresar")
        }
    }
}