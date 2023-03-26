package com.lanazirot.curpavanzado.ui.navgraph

import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.curpavanzado.domain.enums.Routes


fun NavGraphBuilder.ManualNavGraph() {
    composable(Routes.Manual.route) { Text(text = "Manual") }
}
