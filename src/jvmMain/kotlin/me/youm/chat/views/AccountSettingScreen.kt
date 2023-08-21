package me.youm.chat.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.youm.chat.ComposeChat
import me.youm.chat.components.Account
import me.youm.chat.components.HeadImage
import me.youm.chat.theme.LightGreenColor

@Composable
fun AccountSettingScreen(){
    val isUser by remember { mutableStateOf(ComposeChat.user.account == Account.Visitor) }
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "User: ${ComposeChat.user.nickName}",
                style = MaterialTheme.typography.h5,
                color = LightGreenColor.primary,
                fontWeight = FontWeight.Bold
            )
            Surface(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                HeadImage(ComposeChat.user)
            }
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(250.dp)
                    .height(50.dp)
                    .shadow(8.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightGreenColor.error
                )
            ){

                Text(
                    textAlign = TextAlign.Center,
                    text = if(isUser) "Login" else "Logout",
                    color = Color.White,
                    style = MaterialTheme.typography.h5
                )

            }
        }
    }
}