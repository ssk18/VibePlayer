package com.ssk.vibeplayer.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = primaryButton,
    onPrimary = primaryText,
    secondary = accent,
    onSecondary = backgroundSurface,
    background = backgroundSurface,
    onBackground = primaryText,
    surface = backgroundSurface,
    onSurface = primaryText,
    onSurfaceVariant = secondaryText,
    surfaceVariant = hoverButton,
    outline = disabledText
)

@Composable
fun VibePlayerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}