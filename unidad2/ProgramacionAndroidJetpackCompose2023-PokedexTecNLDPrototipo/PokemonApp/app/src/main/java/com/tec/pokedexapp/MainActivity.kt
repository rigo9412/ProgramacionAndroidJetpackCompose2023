package com.tec.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tec.pokedexapp.data.source.Pokemon
import com.tec.pokedexapp.data.source.PokemonAPI
import com.tec.pokedexapp.ui.navigator.graphs.RootGraph
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
                    var assetManager = assets
                    var pokemons = PokemonAPI().getPokemons(assetManager)
                    RootGraph(navController = rememberNavController(),assetManager,pokemons)
                }
            }
        }
    }
}