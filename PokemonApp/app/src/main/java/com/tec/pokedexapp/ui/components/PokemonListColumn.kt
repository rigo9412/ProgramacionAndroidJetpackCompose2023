package com.tec.pokedexapp.ui.components

import android.content.res.AssetManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.model.Pokemon

@Composable
fun PokemonListColumn(pokemonList : List<Pokemon>, assetManager: AssetManager, navController: NavHostController){
    LazyColumn(
        modifier = Modifier.fillMaxHeight().clip(RoundedCornerShape(20.dp)).background(Color.White),
        contentPadding = PaddingValues(start=0.dp,top=0.dp,end = 60.dp,bottom = 60.dp)
    ) {
        items(pokemonList){
            PokemonListField(pokemon = it, assetManager = assetManager,navController)
        }
    }
}