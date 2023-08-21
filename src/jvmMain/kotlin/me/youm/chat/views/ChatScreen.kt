package me.youm.chat.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.youm.chat.ComposeChat
import me.youm.chat.client.packets.Packets
import me.youm.chat.components.*

import java.util.*

@Composable
fun ChatScreen(
    chats: SnapshotStateList<Chat>
) {
    var text by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    LaunchedEffect(chats.size){
        scrollState.animateScrollTo(scrollState.maxValue)
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(top = 88.dp)
    ) {
        Column(
            modifier = Modifier.padding(bottom = 80.dp)
                .verticalScroll(scrollState)
        ) {
            for (message in chats) {
                UserChat(message)
            }
        }
        Bottom(chats, message = text, onTextChange = { text = it })
    }
}
/**
 * chat screen bottom include
 * InputField and SendButton
 */
@Composable
fun Bottom(
    chats: SnapshotStateList<Chat>,
    message: String,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(all = 8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        //Dividing line
        Divider(
            modifier = Modifier.padding(vertical = 5.dp)
                .fillMaxWidth(),
            color = Color(233, 233, 233),
            thickness = 1.5.dp
        )
        Row {
            OutlinedTextField(
                label = { Text("聊天输入框") },
                value = message,
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                onValueChange = { onTextChange(it) },
                shape = RoundedCornerShape(20.dp)
            )
            OutlinedButton(
                modifier = Modifier.height(64.dp).padding(top = 8.dp),
                border = BorderStroke(
                    ButtonDefaults.OutlinedBorderSize,
                    MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                ),
                onClick = {
                    chats.add(Chat(ComposeChat.user,message,Date()))
                    ComposeChat.client.sendMessage(message,Packets.CHAT)
                },
            ) {
                Text("发送消息")
            }
        }
    }
}
