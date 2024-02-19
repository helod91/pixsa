package com.roche.android.bpi.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.br.wcabral.kotlin.android.githubcompose.ui.theme.Typography

private val DarkColorPalette = darkColorScheme(
    primary = BlueRoche,
    secondary = Purple700,
    tertiary = Teal200,
    onPrimary = Color.DarkGray,
    onSurfaceVariant = Color.LightGray
)

private val LightColorPalette = lightColorScheme(
    primary = BlueRoche,
    secondary = Purple700,
    tertiary = Teal200,
    onPrimary = Color.White,
    onSurfaceVariant = Color.LightGray
)

@Composable
fun BpiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

const val SIDE_EFFECT_KEY = "bpi-side-effect-key"