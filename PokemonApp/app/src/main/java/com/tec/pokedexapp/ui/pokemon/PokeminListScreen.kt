package com.tec.pokedexapp.ui.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tec.pokedexapp.ui.navigator.screens.Screens

@Composable
fun PokemonListScreen(navController: NavController){
    Text("POKEDEX")
    Column(){
        Button(onClick = { navController.navigate(Screens.PokemonScreen.passId(1))}) {
            Text("VerPokemon")
        }
    }
}