package com.tec.pokedexapp.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.R
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens

//@Composable
//fun ResultScreen(navController: NavHostController, globalProvider: GlobalProvider,score: Int?, state: String?){
//    Column() {
//
//        //GameState.END
//        //GameState.LOST
//
//        Text("RESULT")
//        Text("Score: $score")
//        Text("State: $state")
//        Button(onClick = {
//            navController.navigate(BottomBarScreens.Home.route){
//                popUpTo(BottomBarScreens.Home.route){inclusive = true}
//            }
//        }){
//            Text("Regresar")
//        }
//    }
//}

@Composable
fun ResultScreen(
    navController: NavHostController,
    globalProvider: GlobalProvider,
    onBackPressed: () -> Unit,
    score: Int?,
    state: String?
){
    val tries = globalProvider.perfilVM.tries.collectAsState()
    BackHandler(onBack = onBackPressed)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundRed)
    ) {
        Header(title = "Resultados:")
        Card(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            )
            {
                Text(text = "Puntaje: ${score!!}", fontWeight = FontWeight.Bold)
                Text(text = "Pokémon descubiertos: ${globalProvider.pokemonVM.getPokemonCount(true,"")}")
                Text(text = "Pokémons perdidos: ${globalProvider.pokemonVM.getPokemonCount(false,"")}")
                if(state == "LOST") {
                    Text(text = "Te quedaste muy cerca, sigue Intentando!!")
                    Image(
                        painter = painterResource(id = R.drawable.goodluck),
                        contentDescription = "Imagen del Pokemon",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                    )
                }
                else{
                    Text(text = "Has completado el Pokedex!!...")
                    Text(text = "Por ahora! Espera a la siguiente generacion!")
                    Text(text = "Intentos: ${tries.value}")
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                        Image(
                            painter = painterResource(id = R.drawable.pokedex_completed),
                            contentDescription = "Pokedex completado",
                            modifier = Modifier.fillMaxSize(0.8f)
                        )
                    }
                }
                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = "Regresar",
                    enabled = true,
                    onClick = {
                        navController.navigate(BottomBarScreens.Home.route){
                            popUpTo(BottomBarScreens.Home.route){inclusive = true}
                        }
                    })
            }
        }
    }
}