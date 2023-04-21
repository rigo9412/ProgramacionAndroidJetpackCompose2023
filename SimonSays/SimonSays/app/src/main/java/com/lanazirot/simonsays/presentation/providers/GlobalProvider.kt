package com.lanazirot.simonsays.presentation.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.lanazirot.simonsays.presentation.pad.PadViewModel

data class GlobalProvider (
    val padViewModel: PadViewModel,
    val nav: NavHostController
)

val LocalGlobalProvider = compositionLocalOf<GlobalProvider> { error("No navigation host controller provided.") }