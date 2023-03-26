package com.lanazirot.curpavanzado.ui.navgraph


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.screens.components.result.CURPResultScreen

fun NavGraphBuilder.ResultNavGraph() {
    composable(Routes.WizardResult.route) {
        CURPResultScreen(
            curp = it.arguments?.getString("curp") ?: "",
            name = it.arguments?.getString("name") ?: "",
            lastname = it.arguments?.getString("lastname") ?: ""
        )
    }
}
