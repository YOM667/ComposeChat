package me.youm.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import me.youm.chat.components.Chat
import me.youm.chat.components.Sex
import me.youm.chat.components.User
import me.youm.chat.navigation.CustomNavigationHost
import me.youm.chat.navigation.composable
import me.youm.chat.navigation.rememberNavController
import me.youm.chat.theme.ComposeTheme
import me.youm.chat.theme.LightGreenColor
import me.youm.chat.views.ChatScreen
import me.youm.chat.views.HomeScreen
import me.youm.chat.views.Screen


val user = User("YouM",Sex.MALE)
@Composable
@Preview
fun App() {
    val navController by rememberNavController(Screen.HomeScreen.name)
    var currentScreen by remember { navController.currentScreen }
    var globalShow by remember { mutableStateOf(false) }
    val chats = remember { mutableStateListOf<Chat>() }
    ComposeTheme(true) {
        Box{
            CustomNavigationHost(navController){navController ->
                navController.composable(Screen.HomeScreen.name){
                    HomeScreen(globalShow) { globalShow = it }
                }
                navController.composable(Screen.SettingsScreen.name){

                }
                navController.composable(Screen.ChatRoomScreen.name){
                    ChatScreen(chats,chats::add)
                }
            }
            Box(
                    modifier = Modifier.shadow(8.dp)
                        .background(LightGreenColor.primary)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .height(80.dp),
            ) {
                AnimatedVisibility(
                    modifier = Modifier.padding(start = 48.dp).align(Alignment.CenterStart),
                    visible = globalShow,
                    enter = fadeIn(),
                    exit = fadeOut()
                ){
                    Row{
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