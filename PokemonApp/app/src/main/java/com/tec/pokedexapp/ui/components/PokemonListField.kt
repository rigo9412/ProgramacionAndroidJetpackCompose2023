package com.tec.pokedexapp.ui.components

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.ui.navigator.screens.Screens

@Preview
@Composable
fun previewField(){
    var pokemon = Pokemon(id = 1,name = "Bulbasaur", type1 = "Grass", type2 = "Poison", total = 318, hp = 45, attack = 49,
        defense = 49, spAtk = 65, spDef = 65, speed = 45, generation = 1, legendary = false)
    PokemonListField(pokemon = pokemon,null,null)
}

@Composable
fun PokemonListField(pokemon: Pokemon, assetManager: AssetManager?, navController: NavHostController?){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = MaterialTheme.colors.background)
            .clickable {
                if (pokemon.discovered) {
                    navController?.navigate(Screens.PokemonScreen.passId(pokemon.id))
                }
            },
    ){
        Text(text = pokemon.id.toString())
        PokemonListFieldImage(modifier = Modifier, assetPath = pokemon.getImagePath(), assetManager = assetManager!!, color = pokemon.discovered)
        Text(text = if(pokemon.discovered) pokemon.name else "???")
    }
}

@Composable
fun PokemonListFieldImage(modifier : Modifier,assetPath: String, assetManager: AssetManager?, color: Boolean){
    val backgroundColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val bitmap = BitmapFactory.decodeStream(assetManager!!.open(assetPath))
    val painter = bitmap.asImageBitmap()
    val filter = if(!color) ColorFilter.lighting(backgroundColor, backgroundColor) else null
    Image(modifier = modifier, bitmap = painter, contentDescription = "Pokemon Image", colorFilter = filter)
}