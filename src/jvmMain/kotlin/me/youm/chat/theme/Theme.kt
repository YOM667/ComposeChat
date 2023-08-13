package me.youm.chat.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val graySurface = Color(0xFF775EEA)
val purple600 = Color(0xFF775EEA)
val purple100 = Color(0xFFDAD2FF)
val purple300 = Color(0xFFC1B7F0)
val lightGray = Color(0xFFD3D3D3)

val DarkPurpleColorPalette = darkColors(
    primary = purple300,
    primaryVariant = purple600,
    secondary = graySurface,
    onSecondary = lightGray,
    onBackground = Color.White,
    onSurface = purple100,
    error = Color.Red,
)

val LightPurpleColorPalette = lightColors(
    primary = purple300,
    primaryVariant = purple600,
    onPrimary = purple300,

    secondary = purple100,
    secondaryVariant = purple600,
    onSecondary = purple300,
    //不能动了
    background = Color.White,
    onBackground = Color.White,
    //不能动了
    surface = Color.White,
    onSurface = purple600,
    //不能动了
    error = Color.Red,
    onError = Color.Red
)
@Composable
fun ComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkPurpleColorPalette
    } else {
        LightPurpleColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}