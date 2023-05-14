package com.tec.pokedexapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tec.pokedexapp.data.constants.DarkRed
import com.tec.pokedexapp.ui.navigator.screens.BottomBarScreens

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreens.Home,
        BottomBarScreens.Pokedex,
        BottomBarScreens.Perfil
    )

    val  navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any {it.route == currentDestination?.route}
    if(bottomBarDestination){
        BottomNavigation(backgroundColor = DarkRed) {
            screens.forEach{screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
){

    BottomNavigationItem(
        label = {Text(text = screen.title)},
        icon = {Icon(imageVector = screen.icon, contentDescription = "Navigation Icon")},
        selected = currentDestination?.hierarchy?.any(){
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}