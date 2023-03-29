package com.otop.chinpokomon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.otop.chinpokomon.data.MainViewModel
import com.otop.chinpokomon.data.screens.MainScreen
import com.otop.chinpokomon.ui.theme.CHIMPOKONTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CHIMPOKONTheme {
                val viewModel: MainViewModel by viewModels()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}


//class PokemonLocalAPI: IPokemonLocalAPI {
//    override fun getPokemons(manager: AssetManager): List<PokemonEntity>{
//        lateinit var jsonString: String
//        try {
//            jsonString = manager.open("pokemons.json")
//                .bufferedReader()
//                .use { it.readText() }
//        }
//        catch (ioException: IOException){
//            print(ioException.message)
//        }
//        val  listPokemon = object : TypeToken<List<PokemonEntity>>(){}.type
//        return Gson().fromJson(jsonString,listPokemon)
//    }
//}