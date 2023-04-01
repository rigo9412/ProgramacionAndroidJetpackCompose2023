package com.tec.pokedexapp.ui.game

import android.annotation.SuppressLint
import android.content.res.AssetManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.components.PokemonListFieldImage
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.screens.Screens
import com.tec.pokedexapp.ui.pokemon.PokemonViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(navController: NavHostController?, globalProvider : GlobalProvider){
    val gameVM = globalProvider.gameVM
    val pokedex = globalProvider.pokemonVM
    gameVM.startRound(pokedex.getRandomUnknownPokemon()!!,pokedex.getRandomPokemonList(3))
    game(gameViewModel = gameVM, pokedex = pokedex, assetManager = globalProvider.assetManager)
}

@Composable
fun game(gameViewModel: GameViewModel, pokedex: PokemonViewModel, assetManager : AssetManager){
    val gameData = gameViewModel.gameModelState.collectAsState()
    val gameState = gameViewModel.gameState.collectAsState()
    val pokemonOptions = gameViewModel.pokemonOptions.collectAsState()
    val currentPokemon = gameViewModel.currentPokemon!!

    Box(modifier = Modifier.fillMaxSize().padding(15.dp)){
        Column(Modifier.fillMaxSize()) {

            PokemonListFieldImage(modifier = Modifier.fillMaxWidth().size(300.dp), assetPath = currentPokemon.getImagePath(),
                assetManager = assetManager,
                color = currentPokemon.discovered)

            gameOptions(pokemonOptions = pokemonOptions.value,
                enabled = (gameState.value != GameState.RESULT),
                makeGuess = {gameViewModel.makeGuess(currentPokemon.id)}
            )
        }
    }
}

@Composable
fun gameOptions(pokemonOptions: List<Pokemon>,enabled: Boolean, makeGuess : (Int) -> Unit){
    Column(modifier = Modifier.padding(10.dp)) {
        pokemonOption(pokemonOptions[0],enabled,makeGuess)
        pokemonOption(pokemonOptions[1],enabled,makeGuess)
        pokemonOption(pokemonOptions[2],enabled,makeGuess)
        pokemonOption(pokemonOptions[3],enabled,makeGuess)
    }
}

@Composable
fun pokemonOption(pokemon : Pokemon,  enabled: Boolean, makeGuess: (Int) -> Unit){
    CustomButton(modifier = Modifier.fillMaxWidth().padding(10.dp), text = pokemon.name, enabled = enabled) {makeGuess(pokemon.id)}
}

//@Preview
//@Composable
//fun gamePrewiew(){
//    game(null, null, null)
//}