package com.almy.poketec.data

import com.almy.poketec.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Game : BottomNavItem("Jugar", R.drawable.game,"game")
    object Statistics: BottomNavItem("Estadísticas",R.drawable.statistics,"statistics")
    object Pokedex: BottomNavItem("Pokedex",R.drawable.pokedex,"pokedex")
}