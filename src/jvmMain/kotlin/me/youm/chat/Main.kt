package me.youm.chat

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import me.youm.chat.components.Sex
import me.youm.chat.components.User
import me.youm.chat.navigation.CustomNavigationHost
import me.youm.chat.navigation.rememberNavController
import me.youm.chat.theme.ComposeTheme
import me.youm.chat.theme.LightPurpleColorPalette
import me.youm.chat.views.Screen


val user = User("YouM",Sex.MALE)
@Composable
@Preview
fun App() {
    val navController by rememberNavController(Screen.HomeScreen.name)
    var currentScreen by remember { navController.currentScreen }
    ComposeTheme {
        Box{
            CustomNavigationHost(navController)
            Box(
                    modifier = Modifier.shadow(8.dp)
                        .background(LightPurpleColorPalette.primary)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .height(80.dp),
            ) {
                Row(
                    modifier = Modifier.padding(start = 48.dp).align(Alignment.CenterStart),
                ){
                    Box (
                        modifier = Modifier.clip(CircleShape)
                            .clickable { currentScreen = Screen.HomeScreen.name },
                    ) {
                        Image(
                            imageVector = Icons.Rounded.Home,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(48.dp),
                            colorFilter = ColorFilter.tint(Color.White),
                        )
                    }
                    Box (
                        modifier = Modifier.padding(start = 32.dp)
                            .clip(CircleShape)
                            .clickable { currentScreen = Screen.ChatRoomScreen.name  },
                    ) {
                        Image(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(48.dp),
                            colorFilter = ColorFilter.tint(Color.White),
                        )
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            size = DpSize(1280.dp,800.dp),
            position = WindowPosition.Aligned(Alignment.Center)
        ),
        title = "ComposeChat",
    ) {
        App()
    }
}