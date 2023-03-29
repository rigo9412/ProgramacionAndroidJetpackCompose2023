package com.otop.chinpokomon

import android.content.res.AssetManager
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.otop.chinpokomon.ui.theme.CHIMPOKONTheme
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CHIMPOKONTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CHIMPOKONTheme {
        Greeting("Android")
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