package com.lanazirot.curpavanzado.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BANNER_GOBIERNO_COLOR,
    primaryVariant = BANNER_GOBIERNO_COLOR_LIGHT,
    secondary = BANNER_GOBIERNO_COLOR_LIGHT
)

private val LightColorPalette = lightColors(
    primary = BANNER_GOBIERNO_COLOR,
    primaryVariant = BANNER_GOBIERNO_COLOR_LIGHT,
    secondary = BANNER_GOBIERNO_COLOR_LIGHT
)

@Composable
fun CURPAvanzadoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}