package com.example.recipe.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val RecipeDarkColorScheme = darkColorScheme(
    primary = PrimaryTeal,
    secondary = SecondaryPink,
    background = DarkBackground,
    surface = SurfaceDark,
    onPrimary = DarkBackground,
    onSecondary = DarkBackground,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
)

@Composable
fun RecipeAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RecipeDarkColorScheme,
        typography = Typography,
        content = content
    )
}
