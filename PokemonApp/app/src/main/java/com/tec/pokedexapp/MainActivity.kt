package com.tec.pokedexapp

import android.content.Context
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
import com.tec.pokedexapp.data.model.Pokemon
import com.tec.pokedexapp.data.model.PokemonEntity
import com.tec.pokedexapp.domain.dao.AppDatabase
import com.tec.pokedexapp.domain.dao.PokemonDao
import com.tec.pokedexapp.domain.dao.PokemonDao_Impl
import com.tec.pokedexapp.ui.game.GameViewModel
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.graphs.RootGraph
import com.tec.pokedexapp.ui.navigator.main.PerfilViewModel
import com.tec.pokedexapp.ui.pokemon.GameViewModelFactory
import com.tec.pokedexapp.ui.pokemon.PokemonViewModel
import com.tec.pokedexapp.ui.pokemon.PokemonViewModelFactory
import com.tec.pokedexapp.ui.theme.PokedexAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
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
                    val context: Context = this
                    val testDao = AppDatabase.getDatabase(context)

                    val pokemonLocalRepository = PokemonLocalRepository(assetManager =  assetManager,pokemonDao = testDao.pokemonDao())

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