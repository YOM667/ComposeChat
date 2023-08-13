package me.youm.chat.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
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
import me.youm.chat.components.*
import me.youm.chat.theme.LightGreenColor
import me.youm.chat.theme.OtherChatBox
import me.youm.chat.theme.UserChatBox
import me.youm.chat.user
import java.util.*

@Composable
fun ChatScreen(
    chats: SnapshotStateList<Chat>,
    addChat: (Chat)->Unit
) {
    var text by remember { mutableStateOf("") }
    var send by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(1f)
            .padding(top = 80.dp)
    ) {

        val scrollState = rememberScrollState()
        LaunchedEffect(chats.size){
            scrollState.animateScrollTo(scrollState.maxValue)
        }
        Column(
            modifier = Modifier.padding(bottom = 80.dp)
                .verticalScroll(scrollState)
        ) {

            if (text.isNotBlank() && send) {
                addChat(Chat(user, text, Type.HIDE, Date()))
                addChat(Chat(User("SB",Sex.FEMALE), text, Type.HIDE, Date()))
                send = !send
                text = ""
            }else{
                send = false
            }
            for (message in chats) {
                if(message.user === user) {
                    AnimatedVisibility(
                        visible = message.type == Type.SHOWN,
                        enter = slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth }
                        )
                    ) {
                        UserChat(user, message.message, message.date, Arrangement.End, UserChatBox,
                            LightGreenColor.primary)
                    }
                }else{
                    AnimatedVisibility(
                        visible = message.type == Type.SHOWN,
                        enter = slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth }
                        )
                    ){
                        UserChat(message.user, message.message,message.date,Arrangement.Start, OtherChatBox,Color.White)
                    }
                }
                message.type = Type.SHOWN
            }
        }
        Column {

            Bottom(
                onSendChange = { send = it },
                message = text,
                onTextChange = { text = it }
            )

        }

    }
}

@Composable
fun Bottom(
    onSendChange: (Boolean) -> Unit,
    message: String,
    onTextChange: (String) -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize().padding(all = 8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ){
        Divider(
            modifier = Modifier.padding(vertical = 5.dp)
                .fillMaxWidth(),
            color = Color(233,233,233),
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
                onClick = { onSendChange(true) }
            ) {
                Text("发送消息")
            }
        }
    }
}


