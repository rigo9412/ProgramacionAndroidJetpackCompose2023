package com.tec.pokedexapp.ui.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.R
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.ui.components.PokemonListFieldImage
import com.tec.pokedexapp.ui.game.Header
import com.tec.pokedexapp.ui.global.GlobalProvider

//@Composable
//fun PokemonScreen(navController: NavHostController){
//    Column() {
//        Text("POKEMON")
//    }
//}
//
//@Composable
//fun PokemonDetails(){
//
//}

@Composable
fun PokemonScreen(globalProvider: GlobalProvider, id: Int?) {
    val pokemon = globalProvider.pokemonVM.getPokemonByID(id!!)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundRed),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(title = "Datos del Pokémon")
        // Primera Card
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                PokemonListFieldImage(
                    modifier = Modifier.padding(top= 16.dp).fillMaxWidth().height(300.dp),
                    assetPath = pokemon.getImagePath(),
                    assetManager = globalProvider.assetManager,
                    color = true)
            }
        }

        // Segunda Card
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Datos del Pokemon 🐾",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )


                //LazyColumn para mostrar los datos del json, borrable.
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    item {
                        Text(text = "Nombre: ${pokemon.name}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Tipo: ${pokemon.type1}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Tipo 2: ${pokemon.type2}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Total: ${pokemon.total}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Vida: ${pokemon.hp}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Ataque: ${pokemon.attack}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Defensa: ${pokemon.defense}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Ataque Especial: ${pokemon.spAtk}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Defensa Especial: ${pokemon.spDef}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Velocidad: ${pokemon.speed}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Generacion: ${pokemon.generation}", fontSize = 16.sp)
                    }
                    item {
                        Text(text = "Legendario: ${if(pokemon.legendary) "Si" else "No"}", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
