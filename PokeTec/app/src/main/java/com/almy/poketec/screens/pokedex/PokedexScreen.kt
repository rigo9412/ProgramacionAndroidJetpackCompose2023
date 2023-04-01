package com.almy.poketec.screens.pokedex

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun FormScreenPokedex(pokemones: List<Pokemon>, viewModel: PokedexViewModel){
    val pokemon = viewModel.uiStatePokemon.collectAsState().value
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is FormUiState.VistaPokemones -> RecyclerView(pokemones, viewModel)
        is FormUiState.DetallePokemon -> PokemonDetails(pokemones,viewModel)
        else -> {
        //TODO
        }
    }
}

@Composable
fun RecyclerView(
    listapokemon: List<Pokemon>,
    viewModel: PokedexViewModel
) {
    Column(){
        LazyVerticalGrid(
            columns = GridCells.Fixed(3) ,
            content = {

                items(listapokemon) { pokemon -> ItemPokemon(pokemon = pokemon){
                    viewModel.asignarPokemon(pokemon)
                    viewModel.PokemonDetails(pokemon)
                }}
            })
    }
}


@Composable
fun ItemPokemon(
    pokemon: Pokemon,
    onPokemonSelected: (Pokemon) -> Unit
) {
    Card(border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .width(200.dp)
            .clickable { onPokemonSelected(pokemon) }
    )
    {
        Column()
        {
            Text(pokemon.id.toString(), Modifier.align(Alignment.CenterHorizontally))
            Image(pokemon.image,pokemon.discover)
            Text(if(pokemon.discover) pokemon.name else "?", Modifier.align(Alignment.CenterHorizontally))

        }
    }
}


@Composable
fun Image(
    ruta:String,
    discover:Boolean
){
    val bitmap = BitmapFactory.decodeStream(LocalContext.current.assets.open(ruta))
    val painter = BitmapPainter(bitmap.asImageBitmap())
    androidx.compose.foundation.Image(
        painter = painter,
        contentDescription = "Pokemon",
        colorFilter = if (discover) null else ColorFilter.tint(Color.Black),
        modifier = Modifier.size(175.dp)
    )
}


@Composable
fun Text(
    Text: String,
    Modifier: Modifier
){
    androidx.compose.material.Text(
        text = Text,
        modifier = Modifier,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun PokemonDetails(
    listapokemon: List<Pokemon>,
    viewModel: PokedexViewModel
) {
    val uiStatePokemon = viewModel.uiStatePokemon.collectAsState().value

    AlertDialog(
        onDismissRequest = { /* Hacer algo al cerrar */ },
        title = { androidx.compose.material.Text(text = "Detalles del Pokemon") },
        confirmButton = {
            TextButton(onClick = { viewModel.VistaPokemones(listapokemon)}) {
                androidx.compose.material.Text("Cerrar")
            }
        },
        text = {
            Column {
                androidx.compose.material.Text("ID: ${uiStatePokemon.id}")
                androidx.compose.material.Text("Nombre: ${uiStatePokemon.name}")
                androidx.compose.material.Text("Tipo 1: ${uiStatePokemon.type1}")
                androidx.compose.material.Text("Tipo 2: ${uiStatePokemon.type2}")
                androidx.compose.material.Text("Total: ${uiStatePokemon.total}")
                androidx.compose.material.Text("HP: ${uiStatePokemon.hp}")
                androidx.compose.material.Text("Ataque: ${uiStatePokemon.attack}")
                androidx.compose.material.Text("Defensa: ${uiStatePokemon.defense}")
                androidx.compose.material.Text("Ataque especial: ${uiStatePokemon.spAtk}")
                androidx.compose.material.Text("Velocidad: ${uiStatePokemon.speed}")
                androidx.compose.material.Text("Generación: ${uiStatePokemon.generation}")
                androidx.compose.material.Text("Legendario: ${uiStatePokemon.legendary}")
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}



