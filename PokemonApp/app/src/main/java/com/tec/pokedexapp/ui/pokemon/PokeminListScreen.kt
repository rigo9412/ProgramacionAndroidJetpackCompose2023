package com.tec.pokedexapp.ui.pokemon

import android.content.res.AssetManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.data.constants.DarkRed
import com.tec.pokedexapp.data.source.Pokemon
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.components.PokemonListColumn
import com.tec.pokedexapp.ui.game.Header
import com.tec.pokedexapp.ui.navigator.screens.Screens

@Preview
@Composable
fun PokedexPreview(){
    PokemonListScreen(null, null,null)
}


@Composable
fun PokemonListScreen(pokemonList : List<Pokemon>?, navController: NavHostController?, assetManager: AssetManager?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundRed)
    ) {
        Column() {
            Header(title = "Pokedex")
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                CustomButton(modifier = Modifier.width(150.dp), text = "Vistos", enabled = true, onClick = {})
                CustomButton(modifier = Modifier, text = "No Vistos",enabled = true,onClick = {})
            }

            PokemonListColumn(pokemonList = pokemonList!!, assetManager = assetManager!!, navController = navController!!)
//        Column(){
//            Button(onClick = { navController?.navigate(Screens.PokemonScreen.passId(1))}) {
//                Text("VerPokemon")
//            }
//        }
        }
    }
}
