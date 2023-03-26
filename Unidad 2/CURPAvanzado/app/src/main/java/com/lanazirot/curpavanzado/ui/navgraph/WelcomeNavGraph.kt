package com.lanazirot.curpavanzado.ui.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.screens.components.welcome.WelcomeScreen


fun NavGraphBuilder.WelcomeNavGraph() {
    composable(route = Routes.Welcome.route) {
        WelcomeScreen()
    }
}