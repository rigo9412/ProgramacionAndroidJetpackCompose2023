package com.otop.chinpokomon.data

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object Settings: Screen("settings")
}