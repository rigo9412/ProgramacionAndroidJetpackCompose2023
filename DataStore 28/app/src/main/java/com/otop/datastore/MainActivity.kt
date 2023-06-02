package com.otop.datastore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.otop.datastore.ui.theme.ThemeManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged



class MainActivity : ComponentActivity() {
    private lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        themeManager = ThemeManager(this)

        val DarkColorScheme = ColorScheme(
            primary = Color(0xFF212121),
            onPrimary = Color.White,
            primaryContainer = Color(0xFF424242),
            onPrimaryContainer = Color.White,
            inversePrimary = Color.White,
            secondary = Color(0xFF757575),
            onSecondary = Color.White,
            secondaryContainer = Color(0xFF9E9E9E),
            onSecondaryContainer = Color.White,
            tertiary = Color.Black, // Aquí debes proporcionar el color adecuado para tu diseño
            onTertiary = Color.White,
            tertiaryContainer = Color.Black, // Aquí debes proporcionar el color adecuado para tu diseño
            onTertiaryContainer = Color.White,
            background = Color(0xFF121212),
            onBackground = Color.White,
            surface = Color(0xFF212121),
            onSurface = Color.White,
            surfaceVariant = Color.Black, // Aquí debes proporcionar el color adecuado para tu diseño
            onSurfaceVariant = Color.White,
            surfaceTint = Color.Black, // Aquí debes proporcionar el color adecuado para tu diseño
            inverseSurface = Color.White,
            inverseOnSurface = Color.Black, // Aquí debes proporcionar el color adecuado para tu diseño
            error = Color(0xFFCF6679),
            onError = Color.White,
            errorContainer = Color(0xFFB00020),
            onErrorContainer = Color.White,
            outline = Color.Black, // Aquí debes proporcionar el color adecuado para tu diseño
            outlineVariant = Color.Black, // Aquí debes proporcionar el color adecuado para tu diseño
            scrim = Color.Black // Aquí debes proporcionar el color adecuado para tu diseño
        )
        val LightColorScheme = ColorScheme(
            primary = Color(0xFFE0E0E0),
            onPrimary = Color.Black,
            primaryContainer = Color(0xFFBDBDBD),
            onPrimaryContainer = Color.Black,
            inversePrimary = Color.Black,
            secondary = Color(0xFF9E9E9E),
            onSecondary = Color.Black,
            secondaryContainer = Color(0xFF757575),
            onSecondaryContainer = Color.Black,
            tertiary = Color.White, // Aquí debes proporcionar el color adecuado para tu diseño
            onTertiary = Color.Black,
            tertiaryContainer = Color.White, // Aquí debes proporcionar el color adecuado para tu diseño
            onTertiaryContainer = Color.Black,
            background = Color.White,
            onBackground = Color.Black,
            surface = Color(0xFFE0E0E0),
            onSurface = Color.Black,
            surfaceVariant = Color.White, // Aquí debes proporcionar el color adecuado para tu diseño
            onSurfaceVariant = Color.Black,
            surfaceTint = Color.White, // Aquí debes proporcionar el color adecuado para tu diseño
            inverseSurface = Color.Black,
            inverseOnSurface = Color.White, // Aquí debes proporcionar el color adecuado para tu diseño
            error = Color(0xFFB00020),
            onError = Color.White,
            errorContainer = Color(0xFFB00020),
            onErrorContainer = Color.White,
            outline = Color.White, // Aquí debes proporcionar el color adecuado para tu diseño
            outlineVariant = Color.White, // Aquí debes proporcionar el color adecuado para tu diseño
            scrim = Color.White // Aquí debes proporcionar el color adecuado para tu diseño
        )
        setContent {
            val isSystemInDarkTheme = isSystemInDarkTheme() // Verifica el tema del sistema

            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme || themeManager.isDarkTheme) {
                    DarkColorScheme
                } else {
                    LightColorScheme
                },
            ) {
                MyApp(themeManager)
            }
        }
    }

}

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun MyApp(themeManager: ThemeManager) {
    val isDarkTheme by themeManager.themeFlow.distinctUntilChanged().collectAsState(false)

    LaunchedEffect(key1 = themeManager) {
        themeManager.themeFlow.collect { isDarkTheme ->
            themeManager.isDarkTheme = isDarkTheme
        }
    }

    Column (modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()){
        Button(
            onClick = { themeManager.toggleTheme() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = if (isDarkTheme) "Modo Claro" else "Modo Oscuro")
        }
    }
}