package me.youm.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.youm.chat.ComposeChat
import me.youm.chat.theme.LightPurpleColor
import me.youm.chat.theme.LightPurpleColor
import me.youm.chat.theme.OtherChatBox
import me.youm.chat.theme.UserChatBox

import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun UserChat(chat: Chat){
    val isNativeUser = chat.user === ComposeChat.user
    val color = if(isNativeUser) LightPurpleColor.primary else Color.White
    val shape = if(isNativeUser) UserChatBox else OtherChatBox
    Surface{
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = if(isNativeUser) Arrangement.End else Arrangement.Start
        ) {
            Column(
                modifier = Modifier.shadow(8.dp, shape)
                    .clip(shape)
                    .background(color)
            ) {
                Row {
                    Text(
                        text = chat.message,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 32.dp, bottom = 4.dp),
                        style = MaterialTheme.typography.subtitle1,
                        color = Color(80,80,80)
                    )
                }
                Text(
                    text = SimpleDateFormat("hh:mm:ss").format(chat.date),
                    modifier = Modifier.padding(4.dp).align(Alignment.End),
                    color = Color(150,150,150),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }

}

data class Chat(
    var user: User,
    var message: String,
    var date: Date
)