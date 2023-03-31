package com.tec.pokedexapp.ui.navigator.main

import android.annotation.SuppressLint
import android.content.res.AssetManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tec.pokedexapp.R
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.data.source.Pokemon
import com.tec.pokedexapp.ui.components.BottomBar
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.navigator.graphs.HomeGraph
import com.tec.pokedexapp.ui.navigator.screens.Screens
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenContainer(navController: NavHostController = rememberNavController(),assetManager: AssetManager, pokemonList: List<Pokemon>){
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {
        HomeGraph(navController = navController,assetManager,pokemonList)
    }
}

@Preview
@Composable
fun previewHome(){
    HomeScreen(null)
}

@Composable
fun HomeScreen(navController: NavHostController?){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundRed)
    ){
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(30.dp)
            ){
                Text(text = "Mi Pokedex", fontWeight = FontWeight.Bold, fontSize =  30.sp, color = Color.White)
                Image(painter = painterResource(R.drawable.pika_home), contentDescription = "Pokedex Logo")
            }

            Row(modifier = Modifier.padding(10.dp)) {
                CustomButton(text = "Jugar", enabled = true, modifier = Modifier.fillMaxWidth()) {
                    navController?.navigate(Screens.GameScreen.route)
                }
            }
        }
    }
}