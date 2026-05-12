package com.example.gramakalyanasports.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = GreenSecondary,
    background = DarkBackground
)

private val LightColors = lightColorScheme(
    primary = GreenPrimary
)

@Composable
fun GramaKalyanaSportsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}