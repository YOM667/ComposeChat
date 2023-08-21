package me.youm.chat

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.*
import me.youm.chat.components.Chat
import me.youm.chat.navigation.Router
import me.youm.chat.theme.ComposeTheme

@Composable
@Preview
fun App() {
    /* when animation is done global show will to be ture */
    val globalShow = remember { mutableStateOf(false) }
    /* all user chat catch */
    val chats = remember { mutableStateListOf<Chat>() }

    ComposeTheme(true) {
        Router(globalShow,chats)
    }
}
fun main(){
    // start client socket
    CoroutineScope(Dispatchers.IO).launch {
        ComposeChat.client.start()
    }
    // start ui render
    application {
        Window(
            onCloseRequest = {
                ComposeChat.client.socket.close()
                this.exitApplication()
                             },
            state = rememberWindowState(
                size = DpSize(1280.dp,800.dp),
                position = WindowPosition.Aligned(Alignment.Center)
            ),
            title = "ComposeChat",
        ) {
            App()
        }
    }
}