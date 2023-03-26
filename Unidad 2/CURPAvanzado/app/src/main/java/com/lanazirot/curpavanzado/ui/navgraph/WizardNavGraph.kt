package com.lanazirot.curpavanzado.ui.navgraph


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.curpavanzado.domain.enums.Routes
import com.lanazirot.curpavanzado.screens.components.wizard.BirthdateScreen
import com.lanazirot.curpavanzado.screens.components.wizard.GenderScreen
import com.lanazirot.curpavanzado.screens.components.wizard.NameScreen
import com.lanazirot.curpavanzado.screens.components.wizard.StateScreen


fun NavGraphBuilder.WizardNavGraph() {

    composable(Routes.WizardName.route) {
        NameScreen()
    }
    composable(Routes.WizardBirthDate.route) {
        BirthdateScreen()
    }
    composable(Routes.WizardGender.route) {
        GenderScreen()
    }
    composable(Routes.WizardState.route) {
        StateScreen()
    }
}