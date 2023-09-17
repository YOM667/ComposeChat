package me.youm.chat.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val purple600 = Color(0xFF775EEA)
val purple100 = Color(0xFFDAD2FF)
val purple300 = Color(0xFFC1B7F0)

val green100 = Color(0xFFA4EA94)
val green300 = Color(0xFF97D788)
val green600 = Color(0xFF7BD067)

val LightGreenColor = lightColors(
    primary = green300,
    primaryVariant = green600,
    onPrimary = Color.White,

    secondary = green100,
    secondaryVariant = green600,
    onSecondary = Color.White,

    background = Color.White,
    onBackground = Color.White,

    surface = Color.White,
    onSurface = green600,

    error = Color(230,40,40),
    onError = Color.White
)

val LightPurpleColor = lightColors(
    primary = purple300,
    primaryVariant = purple600,
    onPrimary = Color.White,

    secondary = purple100,
    secondaryVariant = purple600,
    onSecondary = Color.White,

    background = Color.White,
    onBackground = Color.White,

    surface = purple600,
    onSurface = Color.White,

    error = Color(230,40,40),
    onError = Color.White
)
@Composable
fun ComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LightPurpleColor
    } else {
        LightPurpleColor
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}