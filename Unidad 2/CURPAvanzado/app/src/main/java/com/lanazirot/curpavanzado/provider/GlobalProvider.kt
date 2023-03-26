package com.lanazirot.curpavanzado.provider

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.lanazirot.curpavanzado.screens.viewmodels.WizardViewModel

data class GlobalProvider (
    val wizardVM: WizardViewModel,
    val nav: NavHostController
)

val LocalGlobalProvider = compositionLocalOf<GlobalProvider> { error("No navigation host controller provided.") }