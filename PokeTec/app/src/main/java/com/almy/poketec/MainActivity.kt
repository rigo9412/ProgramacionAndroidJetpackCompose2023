package com.almy.poketec

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.almy.poketec.screens.game.GameScreen1
import com.almy.poketec.screens.game.GameViewModel
import com.almy.poketec.screens.pokedex.FormScreenPokedex
import com.almy.poketec.screens.pokedex.PokedexViewModel
import com.almy.poketec.ui.theme.PokeTecTheme
import com.almy.poketec.ui.theme.navBar
import com.game.guesspoke.screens.game.listaPokedex

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            listaPokemon = ListaPokemon(applicationContext)
            val viewModel: GameViewModel by viewModels()
            val pokedexViewModel: PokedexViewModel by viewModels()
            PokeTecTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //GameScreen1(viewModel = viewModel)
                    MainScreenView(viewModel = viewModel, pokedexViewModel = pokedexViewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    gameViewModel: GameViewModel,
    pokedexViewModel: PokedexViewModel
) {
    NavHost(navController, startDestination = "game") {
        composable("game") {
            GameScreen1(viewModel = gameViewModel)
        }
        composable("statistics") {

        }
        composable("pokedex") {
            FormScreenPokedex(pokemones = listaPokemon, viewModel = pokedexViewModel)
        }
    }
}

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        BottomNavItem.Game,
        BottomNavItem.Statistics,
        BottomNavItem.Pokedex,
    )
    BottomNavigation(
        backgroundColor = navBar,
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
fun MainScreenView(viewModel: GameViewModel, pokedexViewModel: PokedexViewModel){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(navController = navController) }
    ){ innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController, gameViewModel = viewModel, pokedexViewModel = pokedexViewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokeTecTheme {

    }
}