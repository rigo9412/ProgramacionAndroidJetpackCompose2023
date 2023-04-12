package com.tec.pokedexapp.ui.navigator.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route : String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreens(
        route = "INICIO",
        title = "INICIO",
        icon = Icons.Default.Home
    )

    object Pokedex: BottomBarScreens(
        route = "POKEDEX",
        title = "POKEDEX",
        icon = Icons.Default.AddCircle
    )

    object Perfil: BottomBarScreens(
        route = "PERFIL",
        title = "PERFIL",
        icon = Icons.Default.Face
    )
}
