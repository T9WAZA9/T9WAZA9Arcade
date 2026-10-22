package com.t9waza9.arcade.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val T9DarkColorScheme = darkColorScheme(
    primary = T9Orange,
    secondary = T9OrangeVariant,
    background = T9Background,
    surface = T9Surface,
    surfaceVariant = T9SurfaceVariant,
    onBackground = T9OnBackground,
    onSurface = T9OnBackground,
    onSurfaceVariant = T9OnSurfaceVariant,
    error = T9Error
)

private val T9LightColorScheme = lightColorScheme(
    primary = T9Orange,
    secondary = T9OrangeVariant,
    background = T9LightBackground,
    surface = T9LightSurface,
    surfaceVariant = T9LightSurfaceVariant,
    onBackground = T9LightOnBackground,
    onSurface = T9LightOnBackground,
    onSurfaceVariant = T9LightOnSurfaceVariant,
    error = T9Error
)

/**
 * Tema de la app. darkTheme se lee desde AppPreferences en MainActivity
 * y se pasa aqui para que el interruptor de Ajustes tenga efecto real.
 */
@Composable
fun T9Waza9ArcadeTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) T9DarkColorScheme else T9LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = T9Typography,
        content = content
    )
}
