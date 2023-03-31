package com.tec.pokedexapp.ui.components

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tec.pokedexapp.data.source.Pokemon

@Preview
@Composable
fun previewField(){
    var pokemon = Pokemon(id = 1,name = "Bulbasaur", type1 = "Grass", type2 = "Poison", total = 318, hp = 45, attack = 49, 
        defense = 49, spAtk = 65, spDef = 65, speed = 45, generation = 1, legendary = false)
    PokemonListField(pokemon = pokemon,null)
}

@Composable
fun PokemonListField(pokemon: Pokemon,assetManager: AssetManager?){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(30.dp)
    ){
        Text(text = pokemon.id.toString())
        PokemonListFieldImage(assetPath = pokemon.getImagePath(), assetManager = assetManager!!, color = true)
        Text(text = pokemon.name)
    }
}

@Composable
fun PokemonListFieldImage(assetPath: String, assetManager: AssetManager, color: Boolean){
    val bitmap = BitmapFactory.decodeStream(assetManager.open(assetPath))
    val painter = bitmap.asImageBitmap()
    val filter = if(color) ColorFilter.lighting(Color.Black, Color.Black) else null
    Image(bitmap = painter, contentDescription = "Pokemon Image", colorFilter = filter)
}