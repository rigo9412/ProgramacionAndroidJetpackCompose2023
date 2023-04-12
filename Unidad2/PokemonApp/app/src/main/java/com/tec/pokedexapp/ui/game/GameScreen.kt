package com.tec.pokedexapp.ui.game

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.components.CustomLifeCard
import com.tec.pokedexapp.ui.components.PokemonListFieldImage
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.main.PerfilViewModel
import com.tec.pokedexapp.ui.navigator.screens.Screens
import com.tec.pokedexapp.ui.pokemon.PokemonViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(navController: NavHostController?, globalProvider : GlobalProvider,onBackPressed: () -> Unit){
    BackHandler(onBack = onBackPressed)
    val gameVM = globalProvider.gameVM
    game(gameViewModel = gameVM, globalProvider.perfilVM ,assetManager = globalProvider.assetManager, navController)
}

@Composable
fun game(gameViewModel: GameViewModel,
         perfilViewModel: PerfilViewModel,
         assetManager : AssetManager,
         navController: NavHostController?){

    if(!gameViewModel.started){
        gameViewModel.started = true
        gameViewModel.startRound()
    }

    val score = gameViewModel.score.collectAsState()
    val lives = gameViewModel.lives.collectAsState()
    val gameState = gameViewModel.gameState.collectAsState()
    val pokemonOptions = gameViewModel.pokemonOptions.collectAsState()
    var currentPokemon = gameViewModel.currentPokemon.collectAsState()

    if(gameState.value == GameState.END || gameState.value == GameState.LOST){
        if(!gameViewModel.finished) {
            gameViewModel.stopGame()
            gameViewModel.finished = true
            perfilViewModel.addScore(gameViewModel.tempScore)
            perfilViewModel.addTry()
            navController!!.navigate(
                Screens.ResultScreen.passScoreAndState(
                    gameViewModel.tempScore,
                    if (gameState.value == GameState.END) "END" else "LOST"
                )
            )
        }
        return
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)){
        Column(Modifier.fillMaxSize()) {

            CustomLifeCard(lives = lives.value, score = score.value)

            PokemonListFieldImage(modifier = Modifier
                .fillMaxWidth()
                .size(300.dp), assetPath = currentPokemon.value!!.getImagePath(),
                assetManager = assetManager,
                color = currentPokemon.value!!.discovered)

            gameOptions(pokemonOptions = pokemonOptions.value,
                enabled = (gameState.value != GameState.RESULT),
                vm = gameViewModel
            )
        }
    }
}

@Composable
fun gameOptions(pokemonOptions: List<Pokemon>,enabled: Boolean, vm: GameViewModel){
    Column(modifier = Modifier.padding(10.dp)) {
        pokemonOption(pokemonOptions[0],enabled,vm)
        pokemonOption(pokemonOptions[1],enabled,vm)
        pokemonOption(pokemonOptions[2],enabled,vm)
        pokemonOption(pokemonOptions[3],enabled,vm)
    }
}

@Composable
fun pokemonOption(pokemon : Pokemon,  enabled: Boolean, viewModel: GameViewModel){
    val coroutineScope = rememberCoroutineScope()
    CustomButton(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), text = pokemon.name, enabled = enabled) {coroutineScope.launch{viewModel.makeGuess(pokemon.id)}}
}

//@Preview
//@Composable
//fun gamePrewiew(){
//    game(null, null, null)
//}