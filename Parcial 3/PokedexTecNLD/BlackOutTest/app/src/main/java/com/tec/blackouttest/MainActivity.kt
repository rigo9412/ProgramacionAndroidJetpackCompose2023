package com.tec.blackouttest

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tec.blackouttest.ui.theme.BlackOutTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackOutTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val assetManager : AssetManager = assets
                    List(assetManager)
                }
            }
        }
    }
}

@Composable
fun List(assetManager : AssetManager){
    var black by remember{ mutableStateOf(false)}
    var pokemonList = getPokemos(assetManager)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
            for (pokemon in pokemonList) {
                val fileName = String.format("%03d", pokemon.id)
                Row() {
                    ImageFromAsset(
                        assetPath = "images/$fileName.png",
                        assetManager = assetManager,
                        black
                    )
                    Text(pokemon.id.toString())
                    Text(pokemon.name)
                }
            }
        }
    }
}

@Composable
fun ImageFromAsset(assetPath: String, assetManager : AssetManager,color : Boolean){
    val bitmap = BitmapFactory.decodeStream(assetManager.open(assetPath))
    val painter = bitmap.asImageBitmap()
    val filter = if(color) ColorFilter.lighting(Color.Black,Color.Black) else null
    Image(bitmap = painter, contentDescription = "xd", colorFilter = filter)
}

fun getPokemos(assetManager: AssetManager) : List<Pokemon>{
    val inputStream = assetManager.open("pokemon151.json")
    val json = inputStream.bufferedReader().use{it.readText()}
    val gson = Gson()
    val listType = object : TypeToken<List<Pokemon>>(){}.type
    val pokemons = gson.fromJson<List<Pokemon>>(json,listType)
    for(pokemon in pokemons){
        Log.d("POKEMON",pokemon.id.toString())
    }
    return pokemons
}

data class Pokemon(
    val id : Int,
    val name : String,
    val type1 : String,
    val type2 : String,
    val total : Int,
    val hp : Int,
    val attack : Int,
    val defense : Int,
    val spAtk : Int,
    val spDef : Int,
    val speed : Int,
    val generation : Int,
    val legendary : Boolean
)