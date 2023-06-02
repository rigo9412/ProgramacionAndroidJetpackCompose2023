package com.otop.poketest

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.otop.poketest.ui.components.*
import kotlinx.coroutines.delay
import java.io.InputStream


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val currentScreen: Screen by viewModel.currentScreen.collectAsState()

    when (currentScreen) {
        is Screen.Home -> HomeScreen(viewModel)
        is Screen.Pokedex -> PokedexScreen(viewModel)
        is Screen.Profile -> ProfileScreen(viewModel)
        is Screen.Failed -> FailedScreen(viewModel)
        is Screen.Complete -> PokedexCompletedView(viewModel)
        is Screen.Top -> TopScreen(viewModel)
        else -> {}
    }
}




@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    if (viewModel.showDialog.value) {
        ErrorModal(onDismiss = { viewModel.showDialog.value = false })
    }

    LaunchedEffect( viewModel.startCount.value  ){
        if (viewModel.gaming.value ){
            while (viewModel.timer.value >= 0){
                delay(1000)
                viewModel.timer.value--
                if (viewModel.timer.value == 0){
                    viewModel.vidaMenos()
                    delay(2000L)
                    viewModel.showDialog.value = false
                }
            }
        }
    }

    var fondo = ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.fondopoke)))
    var fondo1 = if (!viewModel.darkMode.value){
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.averya)))
    } else {
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.noche)))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(fondo1),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
        {
        if (viewModel.gaming.value){
            var pokemon = viewModel.getRandomPokemon(false)

            val inputStream: InputStream = viewModel.assetMannager.open(viewModel.getImagePath(pokemon.numberID))
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val painter = rememberAsyncImagePainter(bitmap)
            Image(painter = painter,
                contentDescription = "Mi imagen",
                modifier = Modifier.size(width = LocalConfiguration.current.screenWidthDp.dp / 2, height = 400.dp),
                colorFilter = ColorFilter.tint(Color.Black),
            )
                ButtonsWithRandom(viewModel = viewModel, pokemon = pokemon)
        }
        else{
            Row() {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(fondo)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { viewModel.gaming.value = true; viewModel.startCount.value = !viewModel.startCount.value; },
                        shape = RoundedCornerShape(100.dp),
                        modifier = Modifier
                            .background(Color.Transparent)
                            .offset(x = -100.dp, y = -30.dp)
                        ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                    )
                    {
                        Text(text = "PLAY", color = Color.Black, textAlign = TextAlign.Center)
                    }
                    val inputStream: InputStream = viewModel.assetMannager.open("other/pokemonguess.png")
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    val painter = rememberAsyncImagePainter(bitmap)
                    Image(modifier = Modifier.size(300.dp),painter = painter , contentDescription = "Pokemon Guess")
                }
            }
        }
    }
}


@Composable
fun PokedexScreen(viewModel: MainViewModel) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red)) {

        items(viewModel.list) {pokemon ->
            val inputStream: InputStream = viewModel.assetMannager.open(viewModel.getImagePath(pokemon.numberID))
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val painter = rememberAsyncImagePainter(bitmap)
            if (viewModel.viewList.value.contains(pokemon)){
                PokemonCard(pokemon = pokemon,painter,true)
            }
            else{
                PokemonCard(pokemon = pokemon,painter,false)
            }
        }
    }
}

@Composable
fun TopScreen(viewModel: MainViewModel){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White, // Color de fondo opcional
                shape = RectangleShape // Forma del fondo opcional
            )
    ) {
        Image(
            painter = painterResource(R.drawable.cortina), // Ruta de la imagen de fondo
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.Center) {
                val inputStreamSilver: InputStream = viewModel.assetMannager.open("medals/silvermedal.png")
                val bitmapSilver = BitmapFactory.decodeStream(inputStreamSilver)
                val painterSilver = rememberAsyncImagePainter(bitmapSilver)
                ImageWithText(image = painterSilver, text = "Segundo Lugar\n"+viewModel.top10.value[1].nombre+"\n"+viewModel.top10.value[1].pais, modifierImg = Modifier.size(110.dp), modifierBox = Modifier.offset(40.dp,200.dp))

                val inputStreamgold: InputStream = viewModel.assetMannager.open("medals/goldmedal.png")
                val bitmapgold = BitmapFactory.decodeStream(inputStreamgold)
                val paintergold = rememberAsyncImagePainter(bitmapgold)
                ImageWithText(image = paintergold, text = "Primer Lugar \n"+viewModel.top10.value[0].nombre+"\n"+viewModel.top10.value[0].pais,modifierImg = Modifier.size(180.dp), modifierBox = Modifier)

                val inputStreamCopper: InputStream = viewModel.assetMannager.open("medals/coppermedal.png")
                val bitmapCopper = BitmapFactory.decodeStream(inputStreamCopper)
                val painterCopper = rememberAsyncImagePainter(bitmapCopper)
                ImageWithText(image = painterCopper, text = "Tercer Lugar\n"+viewModel.top10.value[2].nombre+"\n"+viewModel.top10.value[2].pais,modifierImg = Modifier.size(110.dp), modifierBox = Modifier.offset((-40).dp,200.dp))
            }
            Row(modifier = Modifier.offset(0.dp,140.dp)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    var c = 0
                    items(viewModel.top10.value) { item ->
                        if (c <= 2)
                        {
                            c++
                        }
                        else{
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(2.dp, Color.Gray),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row() {
                                        Text(
                                            text = item.nombre,
                                            modifier = Modifier.padding(16.dp),
                                            style = MaterialTheme.typography.body1
                                        )
                                        Text(
                                            text = item.pais,
                                            modifier = Modifier.padding(16.dp),
                                            style = MaterialTheme.typography.body1
                                        )
                                        Text(
                                            text = item.intentos.toString(),
                                            modifier = Modifier.padding(16.dp),
                                            style = MaterialTheme.typography.body1
                                        )
                                        Text(
                                            text = item.tiempo.toString(),
                                            modifier = Modifier.padding(16.dp),
                                            style = MaterialTheme.typography.body1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun ProfileScreen(viewModel: MainViewModel) {
    ProfileCard(viewModel = viewModel)
}

@Composable
fun FailedScreen(viewModel: MainViewModel) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxSize()
        ) {
        Image(painter = painterResource(id = R.drawable.pikallora), contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)

    }
    Text(text = "Perdistes XD", fontSize = 40.sp, color = Color.Black, modifier = Modifier.offset(80.dp,0.dp))
}
