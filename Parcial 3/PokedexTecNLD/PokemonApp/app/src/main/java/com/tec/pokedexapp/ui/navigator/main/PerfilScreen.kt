package com.tec.pokedexapp.ui.navigator.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.R
import com.tec.pokedexapp.ui.game.Header
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.screens.Screens

//@Composable
//fun PerfilScreenPLACEHOLDER(navController: NavController){
//    Text("PERFIL")
//}

@Composable
fun PerfilScreen(navController: NavController,globalProvider: GlobalProvider) {
    val pokedex = globalProvider.pokemonVM
    val perfil = globalProvider.perfilVM
    val user = perfil.user.collectAsState().value
    val pokemonlists = pokedex.pokedexState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundRed)
    ){
        Header(title = "Datos del Entrenador")
        Card(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(modifier = Modifier.padding(end = 16.dp, top = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp))
                {
                    Text(text = "Datos del Entrenador", fontWeight = FontWeight.Bold)
                    Text(text = "Pokémon Vistos: ${pokemonlists.viewedPokemon.size}")
                    Text(text = "Pokémon Desconocidos: ${pokemonlists.unknownPokemon.size}")
                    Text(text = "Pkm Legendarios Vistos: ${pokedex.getLegendaryPokemonCount()}")
                    Text(text = "Medallas: ${if(pokedex.completedPokedex()) "\uD83C\uDFC5" else ""}")

                    Button(onClick = {
                        perfil.reiniciarProgreso()
                        pokedex.resetPokedex()
                    }){
                        Text("Reiniciar progreso")
                    }

//                    //NAV A LEADERBOARD
//                    Button(onClick = {navController?.navigate(Screens.LeaderBoardScreen.route)}){
//                        Text("Ver leaderboard")
//                    }

                }
                //Caja imagen
                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .width(100.dp)
                        .background(Color.LightGray)
                        .align(Alignment.TopEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trainer),
                        contentDescription = "Imagen de Perfil",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp) ,
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Top 3 Puntajes Recientes", fontWeight = FontWeight.Bold)
                Text(text = "Primer puntaje 🥇: ${user.topScore1}")
                Text(text = "Segundo puntaje 🥈:${user.topScore2}")
                Text(text = "Tercer puntaje 🥉: ${user.topScore3}")
            }
        }

        Card(
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, bottom = 65.dp)
                .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Pokémon vistos por tipo:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))
                LazyColumn() {
                    item { Text(text = "🌱 Planta: Vistos: ${pokedex.getPokemonCount(true,"Grass")}, Restantes: ${pokedex.getPokemonCount(false,"Grass")}") }
                    item { Text(text = "🔥 Fuego: Vistos: ${pokedex.getPokemonCount(true,"Fire")}, Restantes: ${pokedex.getPokemonCount(false,"Fire")}") }
                    item { Text(text = "💧 Agua: Vistos: ${pokedex.getPokemonCount(true,"Water")}, Restantes: ${pokedex.getPokemonCount(false,"Water")}") }
                    item { Text(text = "🐦 Volador: Vistos: ${pokedex.getPokemonCount(true,"Flying")}, Restantes: ${pokedex.getPokemonCount(false,"Flying")}") }
                    item { Text(text = "🐁 Normal: Vistos: ${pokedex.getPokemonCount(true,"Normal")}, Restantes: ${pokedex.getPokemonCount(false,"Normal")}") }
                    item { Text(text = "🥊 Lucha: Vistos: ${pokedex.getPokemonCount(true,"Fighting")}, Restantes: ${pokedex.getPokemonCount(false,"Fighting")}") }
                    item { Text(text = "☠ Veneno: Vistos: ${pokedex.getPokemonCount(true,"Poison")}, Restantes: ${pokedex.getPokemonCount(false,"Poison")}") }
                    item { Text(text = "🌎 Tierra: Vistos: ${pokedex.getPokemonCount(true,"Ground")}, Restantes: ${pokedex.getPokemonCount(false,"Ground")}") }
                    item { Text(text = "🧱 Roca: Vistos: ${pokedex.getPokemonCount(true,"Rock")}, Restantes: ${pokedex.getPokemonCount(false,"Rock")}") }
                    item { Text(text = "🐛 Bicho: Vistos: ${pokedex.getPokemonCount(true,"Bug")}, Restantes: ${pokedex.getPokemonCount(false,"Bug")}") }
                    item { Text(text = "👻 Fantasma: Vistos: ${pokedex.getPokemonCount(true,"Ghost")}, Restantes: ${pokedex.getPokemonCount(false,"Ghost")}") }
                    item { Text(text = "⚡ Eléctrico: Vistos: ${pokedex.getPokemonCount(true,"Electric")}, Restantes: ${pokedex.getPokemonCount(false,"Electric")}") }
                    item { Text(text = "🔮 Psíquico: Vistos: ${pokedex.getPokemonCount(true,"Psychic")}, Restantes: ${pokedex.getPokemonCount(false,"Psychic")}") }
                    item { Text(text = "❄ Hielo: Vistos: ${pokedex.getPokemonCount(true,"Ice")}, Restantes: ${pokedex.getPokemonCount(false,"Ice")}") }
                    item { Text(text = "🐲 Dragón: Vistos: ${pokedex.getPokemonCount(true,"Dragon")}, Restantes: ${pokedex.getPokemonCount(false,"Dragon")}") }
                    item { Text(text = "") }
                    item { Text(text = "") }
                }
            }
        }
    }
}