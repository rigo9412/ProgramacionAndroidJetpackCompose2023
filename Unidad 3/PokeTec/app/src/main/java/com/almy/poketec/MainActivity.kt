package com.almy.poketec

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.almy.poketec.data.BottomNavItem
import com.almy.poketec.data.ListaPokemon
import com.almy.poketec.data.listaPokemon
import com.almy.poketec.data.records.Player
import com.almy.poketec.data.records.currentPlayer
import com.almy.poketec.data.records.players
import com.almy.poketec.screens.game.GameScreen1
import com.almy.poketec.screens.game.GameViewModel
import com.almy.poketec.screens.pokedex.FormScreenPokedex
import com.almy.poketec.screens.pokedex.PokedexViewModel
import com.almy.poketec.screens.usuario.PantallaUsuario.Usuario
import com.almy.poketec.ui.theme.PokeTecTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkMode = remember{ mutableStateOf(false) }
            listaPokemon = ListaPokemon(applicationContext)
            val viewModel: GameViewModel by viewModels()
            val pokedexViewModel: PokedexViewModel by viewModels()

            val createPlayers = remember{ mutableStateOf(true) }

            PokeTecTheme(darkTheme = darkMode.value){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    //esto es solo para llenar la pokedex
                    /*listaPokemon.forEach{
                        if(it.id != 151){
                            it.discover = true
                        }
                    }*/
                    MainScreenView(viewModel = viewModel, pokedexViewModel = pokedexViewModel,darkMode)
                }
            }
        }
    }
}

fun CreatePlayers()
{
    var player1: Player = Player()
    player1.name = "Claudio"
    player1.country = "México"
    player1.id = 1
    player1.time = 70
    player1.attemps = 14
    //player1.pokedex = listaPokemon
    player1.pokedex.forEach {
            it.discover = true
    }
    player1.score = CalculateScore(player1)

    var player2: Player = Player()
    player2.name = "Myriam"
    player2.country = "México"
    player2.id = 2
    player2.time = 61
    player2.attemps = 17
    //player2.pokedex = listaPokemon as MutableList<Pokemon>
    player2.pokedex.forEach {
            it.discover = true
    }
    player2.score = CalculateScore(player2)

    var player3: Player = Player()
    player3.name = "Aldo"
    player3.country = "México"
    player3.id = 3
    player3.time = 63
    player3.attemps = 11
    //player3.pokedex = listaPokemon as MutableList<Pokemon>
    player3.pokedex.forEach {
            it.discover = true
    }
    player3.score = CalculateScore(player3)

    var player4: Player = Player()
    player4.name = "Gema"
    player4.country = "México"
    player4.id = 4
    player4.time = 68
    player4.attemps = 14
    //player4.pokedex = listaPokemon as MutableList<Pokemon>
    player4.pokedex.forEach {
        it.discover = true
    }
    player4.score = CalculateScore(player4)

    players.add(player1)
    players.add(player2)
    players.add(player3)
    players.add(player4)
}

fun CalculateScore(player: Player): Int
{
    var score = 0
    player.pokedex.forEach {
        if(it.discover == true)
        {
            score++
        }
    }
    score = score * 15
    score = score - player.attemps - player.time
    return score
}

@Composable
fun MyFAB(
    darkMode: MutableState<Boolean>
){
    FloatingActionButton(onClick = {darkMode.value = !darkMode.value}, backgroundColor = Color.Blue, contentColor = Color.Black) {
        Icon(imageVector = if(darkMode.value){
            Icons.Default.LightMode}
        else{
            Icons.Default.DarkMode
        },
            contentDescription = "add")
    }
}




@Composable
fun NavigationGraph(
    navController: NavHostController,
    gameViewModel: GameViewModel,
    pokedexViewModel: PokedexViewModel,
    darkMode: MutableState<Boolean>
) {
    NavHost(navController, startDestination = "game") {
        composable("game") {
            GameScreen1(viewModel = gameViewModel, darkMode)
        }
        composable("statistics") {
            if(currentPlayer == null)
            {
                Mensaje()
            }
            else{
                currentPlayer?.pokedex?.let { it1 -> Usuario(Listaxd = it1) }
            }
        }
        composable("pokedex") {
            if(currentPlayer == null)
            {
                Mensaje()
            }
            else{
                currentPlayer?.let { it1 ->
                    FormScreenPokedex(pokemones = it1.pokedex,
                        viewModel = pokedexViewModel)
                }
            }
        }
    }
}

@Composable
fun BottomNav(navController: NavController, darkMode: MutableState<Boolean>) {
    val items = listOf(
        BottomNavItem.Game,
        BottomNavItem.Statistics,
        BottomNavItem.Pokedex,
    )
    BottomNavigation(
        backgroundColor = if(darkMode.value) Color(0xFF0032c50) else Color(0xFF3aa0f9),
        contentColor = Color.Black,
        modifier = Modifier.height(75.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(viewModel: GameViewModel, pokedexViewModel: PokedexViewModel,darkMode: MutableState<Boolean>){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNav(navController = navController,darkMode) },
        floatingActionButton = { MyFAB(darkMode = darkMode)},
    ){
            innerPadding ->

// Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {

            NavigationGraph(navController = navController, gameViewModel = viewModel, pokedexViewModel = pokedexViewModel, darkMode)
        }
    }
}

@Composable
fun Mensaje()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Necesito saber quien eres :)")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokeTecTheme {

    }
}