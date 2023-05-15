package com.tec.pokedexapp.ui.game

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tec.pokedexapp.R
import com.tec.pokedexapp.data.constants.BackgroundRed
import com.tec.pokedexapp.data.constants.countries
import com.tec.pokedexapp.ui.components.CustomButton
import com.tec.pokedexapp.ui.global.GlobalProvider
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens
import java.util.*

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
    Log.d("COUNTRY", countries[0].toString())
    val user = globalProvider.perfilVM.user.collectAsState().value
    val finished = globalProvider.perfilVM.finished.collectAsState().value
    val expanded = remember { mutableStateOf(false) }

    BackHandler(onBack = onBackPressed)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundRed)
    ) {
        Header(title = "Resultados:")
        Card(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
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
                    globalProvider.perfilVM.setFinishDate()
                    Text(text = "Has completado el Pokedex!!...")
                    Text(text = "Por ahora! Espera a la siguiente generacion!")
                    Text(text = "Intentos: ${user.triesToFinish}")
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                        Image(
                            painter = painterResource(id = R.drawable.pokedex_completed),
                            contentDescription = "Pokedex completado",
                            modifier = Modifier.fillMaxSize(0.6f)
                        )
                    }

                    if(!finished) {
                        Text("Nombre:")
                        TextField(
                            value = user.name,
                            onValueChange = { globalProvider.perfilVM.setName(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
                        )
                        Text("Pais:")
                        TextField(
                            value = Locale("", user.country).displayCountry,
                            onValueChange = {},
                            enabled = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded.value = true }
                                .fillMaxWidth()
                        )
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false }
                        ) {
                            for (countryCode in countries) {
                                val country = Locale("", countryCode)
                                DropdownMenuItem(
                                    onClick = {
                                        globalProvider.perfilVM.setCountry(countryCode)
                                        expanded.value = false
                                    }
                                ) {
                                    Text(text = country.displayCountry)
                                }
                            }
                        }
                    }
                }


                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    text = "Regresar",
                    enabled = true,
                    onClick = {
                        if(state != "LOST" && !finished) {
                            if(user.name != "" && user.country != "") {
                                globalProvider.perfilVM.updateUser()
                                globalProvider.perfilVM.finish(true)
                                globalProvider.leaderboardVM.postUser(user)
                                navController.navigate(BottomBarScreens.Home.route) {
                                    popUpTo(BottomBarScreens.Home.route) { inclusive = true }
                                }
                            }
                        }
                        else{
                            navController.navigate(BottomBarScreens.Home.route) {
                                popUpTo(BottomBarScreens.Home.route) { inclusive = true }
                            }
                        }
                    })
            }
        }
    }
}