package com.tec.pokedexapp.ui.navigator.main

import android.annotation.SuppressLint
import android.content.res.AssetManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.tec.pokedexapp.R
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.ui.components.BottomBar
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.game.Header
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.graphs.HomeGraph
import com.tec.pokedexapp.ui.navigator.screens.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenContainer(navController: NavHostController = rememberNavController(),globalProvider: GlobalProvider){
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {
        HomeGraph(navController = navController,globalProvider)
    }
}

//@Preview
//@Composable
//fun previewHome(){
//    HomeScreen(null,null)
//}

@Composable
fun HomeScreen(navController: NavHostController?,globalProvider: GlobalProvider){
    BoxWithBackground(
        backgroundId = R.drawable.fondo1,
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(modifier = Modifier.padding(top = 50.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
            ){
                Text(text = "Mi Pokedex", fontWeight = FontWeight.Bold, fontSize =  30.sp, color = Color.White)
                Image(painter = painterResource(R.drawable.pika_home), contentDescription = "Pokedex Logo")
            }
            Row(modifier = Modifier.padding(10.dp)) {
                CustomButton(text = "Jugar", enabled = true, modifier = Modifier.fillMaxWidth().padding(top = 100.dp)) {
                    globalProvider.gameVM.finished = false
                    globalProvider.gameVM.started = false
                    navController?.navigate(Screens.GameScreen.route)
                }
            }
        }
    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BackgroundRed)
//    ){
//        Column() {
//            Row(
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//                    .padding(30.dp)
//            ){
//                Text(text = "Mi Pokedex", fontWeight = FontWeight.Bold, fontSize =  30.sp, color = Color.White)
//                Image(painter = painterResource(R.drawable.pika_home), contentDescription = "Pokedex Logo")
//            }
//
//            Row(modifier = Modifier.padding(10.dp)) {
//                CustomButton(text = "Jugar", enabled = true, modifier = Modifier.fillMaxWidth()) {
//                    globalProvider.gameVM.finished = false
//                    globalProvider.gameVM.started = false
//                    navController?.navigate(Screens.GameScreen.route)
//                }
//            }
//        }
//    }
}

@Composable
fun BoxWithBackground(
    backgroundId: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    content: @Composable () -> Unit
) {
    val painter = rememberImagePainter(backgroundId)
    Box(modifier = modifier) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}