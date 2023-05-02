package com.lanazirot.simonsays.ui.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.lanazirot.simonsays.ui.common.components.ui.pad.PadViewModel

data class GlobalProvider (
    val padViewModel: PadViewModel,
    val nav: NavHostController
)

val LocalGlobalProvider = compositionLocalOf<GlobalProvider> { error("No navigation host controller provided.") }