package com.almy.poketec.screens.pokedexCompleted

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.almy.poketec.screens.game.GameViewModel


@Composable
fun FormScreenPokedexCompleted(viewModel: GameViewModel){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageFondo("images/WhiteScreen.jpg", true)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FelicidadesScreen(viewModel)
        }
    }
}



@Composable
fun FelicidadesScreen(viewModel: GameViewModel) {
    Text(
        text = "¡FELICIDADES!",
        style = TextStyle(
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    )
    Spacer(modifier = Modifier.height(18.dp))
    Image("images/Felicidades.png", true)
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "¡Has descubierto a todos los pokemones!",
        style = TextStyle(fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center)
    )

    Spacer(modifier = Modifier.height(260.dp))
    ClickableText(
        text = AnnotatedString("Volver a inicio"),
        style = TextStyle(
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        onClick = {
            viewModel.Inicio()
        }
    )
}


@Composable
fun Image(ruta:String,discover:Boolean){
    val bitmap = BitmapFactory.decodeStream(LocalContext.current.assets.open(ruta))
    val painter = BitmapPainter(bitmap.asImageBitmap())
    androidx.compose.foundation.Image(
        painter = painter,
        contentDescription = "Pokemon",
        colorFilter = if (discover) null else ColorFilter.tint(Color.Black),
        modifier = Modifier.size(175.dp)
    )
}



@Composable
fun ImageFondo(ruta:String,discover:Boolean){
    val bitmap = BitmapFactory.decodeStream(LocalContext.current.assets.open(ruta))
    val painter = BitmapPainter(bitmap.asImageBitmap())
    androidx.compose.foundation.Image(
        painter = painter,
        contentDescription = "Pokemon",
        colorFilter = if (discover) null else ColorFilter.tint(Color.Black),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}
