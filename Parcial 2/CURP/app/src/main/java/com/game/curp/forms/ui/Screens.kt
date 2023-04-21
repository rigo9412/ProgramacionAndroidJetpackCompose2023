package com.game.curp.forms.ui

sealed class Screens (val route: String){
    object Form: Screens(route = "form")
    object Result: Screens("result?curp={curp}")
}