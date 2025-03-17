package com.android.androidtestassignment.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


//Only use one color scheme as there is no design for the dark mode
private val ColorScheme = lightColorScheme(
    primary = RedCoral,
    secondary = Pink40,
    tertiary = Pink40,
    surface = RedCoral,
    onSurface = Color.White,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

//There are no dark mode designs so we'll ignore it
@Composable
fun AndroidTestAssignmentTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}