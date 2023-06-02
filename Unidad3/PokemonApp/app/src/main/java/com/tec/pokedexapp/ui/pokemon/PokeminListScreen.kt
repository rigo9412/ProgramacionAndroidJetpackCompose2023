package com.tec.pokedexapp.ui.pokemon

import android.content.res.AssetManager
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.components.PokemonListColumn
import com.tec.pokedexapp.ui.game.Header
import com.tec.pokedexapp.ui.global.GlobalProvider

@Composable
fun PokemonListScreen(navControler: NavHostController, globalProvider: GlobalProvider){
    PokemonList(globalProvider,navControler)
}

@Composable
fun PokemonList(globalProvider: GlobalProvider, navController: NavHostController?) {
    var lista by remember { mutableStateOf(ListType.FULL)}
    var pokedexState = globalProvider.pokemonVM.pokedexState.collectAsState()

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
                    .padding(5.dp)
            ) {
                CustomButton(modifier = Modifier.width(150.dp).height(55.dp), text = "Vistos", enabled = true, onClick = {lista = ListType.VIEWED})
                CustomButton(modifier = Modifier.height(55.dp), text = "No Vistos",enabled = true,onClick = {lista = ListType.NONVIEWED})
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                CustomButton(modifier = Modifier.height(55.dp),text = "Todos",enabled = true, onClick = { lista = ListType.FULL })
            }
            Box(modifier = Modifier
                .padding(20.dp, 20.dp, 20.dp, 60.dp)
                .fillMaxSize())
            {
                PokemonListColumn(
                    pokemonList = when(lista){
                        ListType.FULL -> pokedexState.value.fullPokemon
                        ListType.VIEWED -> pokedexState.value.viewedPokemon
                        ListType.NONVIEWED -> pokedexState.value.unknownPokemon
                    },
                    assetManager = globalProvider.assetManager!!,
                    navController = navController!!
                )
            }
//        Column(){
//            Button(onClick = { navController?.navigate(Screens.PokemonScreen.passId(1))}) {
//                Text("VerPokemon")
//            }
//        }
        }
    }
}

enum class ListType{
    VIEWED, NONVIEWED, FULL
}
