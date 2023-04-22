package com.otop.poketest.ui.components

import android.content.res.AssetManager
import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.otop.poketest.data.PokemonEntity

@Composable
fun PokemonCard(pokemon: PokemonEntity,painter:Painter, isView: Boolean ,modifier: Modifier = Modifier) {
    var showModal by remember { mutableStateOf(false) }

    if (showModal) {
        AlertDialog(
            onDismissRequest = { showModal = false },
            title = { Text(text = if (isView) pokemon.name+" Stats" else "----------" ) },
            text = {
                Row() {
                    Column() {
                        Text(
                            text = "Type 1: ${pokemon.type1}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Type 2: ${pokemon.type2}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Total: ${pokemon.total} m",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "HP: ${pokemon.hp}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Attack: ${pokemon.attack}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Defense: ${pokemon.defense}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Column() {
                        Text(
                            text = "Speed Atk: ${pokemon.spAtk}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Speed Def: ${pokemon.spDef}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Speed: ${pokemon.speed}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Generation: ${pokemon.generation}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Legendary: ${false}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showModal = false }
                ) {
                    Text(text = "Ok")
                }
            }
        )
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp

    ) {
        Image(
            modifier = Modifier
                .clickable { showModal = true },
            painter = painterResource(id = com.otop.poketest.R.drawable.pokedexchido),
            contentDescription = null, contentScale = ContentScale.Crop
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.offset(0.dp,140.dp)){
            if ( isView ){
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(shape = CircleShape),
                    painter = painter,
                    contentDescription = "Picture of ${pokemon.name}",
                )
            }
            else {
                Text(
                    text = "----------",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painter,
                    contentDescription = "Picture of ${pokemon.name}",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .size(200.dp)
                        .clip(shape = CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


