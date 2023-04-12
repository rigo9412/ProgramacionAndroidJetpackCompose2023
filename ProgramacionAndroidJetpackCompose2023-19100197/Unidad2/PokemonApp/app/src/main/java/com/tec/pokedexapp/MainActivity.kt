package com.tec.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.tec.pokedexapp.data.PokemonLocalRepository
import com.tec.pokedexapp.ui.game.GameViewModel
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.graphs.RootGraph
import com.tec.pokedexapp.ui.navigator.main.PerfilViewModel
import com.tec.pokedexapp.ui.pokemon.GameViewModelFactory
import com.tec.pokedexapp.ui.pokemon.PokemonViewModel
import com.tec.pokedexapp.ui.pokemon.PokemonViewModelFactory
import com.tec.pokedexapp.ui.theme.PokedexAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val assetManager = assets
                    val navController = rememberNavController()

                    val pokemonLocalRepository = PokemonLocalRepository(assetManager)

                    val pokedexVM : PokemonViewModel by viewModels{ PokemonViewModelFactory(pokemonLocalRepository) }
                    val perfilVM : PerfilViewModel by viewModels()
                    val gameVM : GameViewModel by viewModels{ GameViewModelFactory(pokedexVM) }

                    val gp = GlobalProvider(
                        gameVM = gameVM,
                        perfilVM = perfilVM,
                        pokemonVM = pokedexVM,
                        assetManager = assetManager,
                        nav = navController
                    )

                    RootGraph(globalProvider = gp)
                }
            }
        }
    }
}