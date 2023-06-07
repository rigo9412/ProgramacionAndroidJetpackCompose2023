package com.tec.appnotas.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.tec.appnotas.data.constants.DarkTopBarColor
import com.tec.appnotas.data.constants.TopBarColor

//Variante oscura
private val DarkColorPalette = darkColors(
    primary = DarkTopBarColor,
    primaryVariant = GrisSelectedOscuro,
    secondary = AzulOscuro
)

//Variante clara
private val LightColorPalette = lightColors(
    primary = TopBarColor,
    primaryVariant = GrisSelectedClaro,
    secondary = AzulClaro

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AppnotasTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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