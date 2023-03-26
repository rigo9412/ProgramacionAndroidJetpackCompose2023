package com.lanazirot.curpavanzado.ui.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.screens.components.manual.ManualScreen


fun NavGraphBuilder.ManualNavGraph() {
    composable(Routes.Manual.route) {
        ManualScreen()
    }
}
