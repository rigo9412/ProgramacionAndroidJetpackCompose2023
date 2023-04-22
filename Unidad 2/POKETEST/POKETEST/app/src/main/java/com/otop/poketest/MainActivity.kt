package com.otop.poketest

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.otop.poketest.data.source.PokemonLocalAPI
import com.otop.poketest.ui.theme.POKETESTTheme
import com.otop.poketest.ui.theme.Rojoenojon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.InputStream

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POKETESTTheme {

                val pokemonLocalAPI = PokemonLocalAPI()
                val assetManager = applicationContext.assets
                val pokemonList = pokemonLocalAPI.getPokemons(assetManager)

                // A surface container using the 'background' color from the theme
                val viewModel: MainViewModel by viewModels()
                viewModel.list = pokemonList
                viewModel.assetMannager = assetManager
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(bottomBar = { footer(viewModel = viewModel) }, topBar = { topBar(viewModel= viewModel)}) {
                        MainScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun footer(viewModel: MainViewModel){
    if (!viewModel.gaming.value){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { viewModel.navigateToHome() }, colors = ButtonDefaults.buttonColors(backgroundColor = Rojoenojon)) {
                Text(text = "Inicio")
            }
            Button(onClick = { viewModel.navigateToPokedex() }, colors = ButtonDefaults.buttonColors(backgroundColor = Rojoenojon)) {
                Text(text = "Pokedex")
            }
            Button(onClick = { viewModel.navigateToProfile() }, colors = ButtonDefaults.buttonColors(backgroundColor = Rojoenojon)) {
                Text(text = "Perfil")
            }
        }
        Switch(checked = viewModel.darkMode.value,
            onCheckedChange = {viewModel.darkMode.value = it},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun topBar(viewModel: MainViewModel) {
    val inputStream: InputStream = viewModel.assetMannager.open("Vida/Vida.png")
    val bitmap = BitmapFactory.decodeStream(inputStream)

    val inputStreamOff: InputStream = viewModel.assetMannager.open("Vida/Vidaquitada.png")
    val bitmapOff = BitmapFactory.decodeStream(inputStreamOff)

    var painter = rememberAsyncImagePainter(bitmap)
    var painterOff = rememberAsyncImagePainter(bitmapOff)
    
    if (viewModel.gaming.value){
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Time: ${viewModel.timer.value}", modifier = Modifier.offset(20.dp,15.dp))
            Text(text = "Score: ${viewModel.viewList.value.size}", modifier = Modifier.offset(-140.dp,15.dp))
            for (i in 0..2){
                if ( viewModel.vidas.value[i] == 1 ){
                    Image(painter = painter,
                        contentDescription = "Mi imagen",
                        modifier = Modifier.size(width = 50.dp, height = 50.dp)
                    )
                }
                else{
                    Image(painter = painterOff,
                        contentDescription = "Mi imagen",
                        modifier = Modifier.size(width = 50.dp, height = 50.dp)
                    )
                }
            }
        }
    }
}