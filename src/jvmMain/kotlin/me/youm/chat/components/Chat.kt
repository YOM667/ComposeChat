package me.youm.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun UserChat(
    user: User,
    message: String,
    date: Date,
    arrangement : Arrangement.Horizontal,
    shape: Shape,
    color: Color
){
    Surface {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = arrangement,
        ) {
            if(arrangement == Arrangement.Start){
                Image(
                    painter = painterResource("images/head.jpg"),
                    contentDescription = "your profile head image",
                    modifier = Modifier.padding(top = 16.dp)
                        .size(64.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Column(
                modifier = Modifier.shadow(8.dp, shape)
                    .clip(shape)
                    .background(color)
            ) {
                Row {
                    Text(
                        text = message,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 32.dp, bottom = 4.dp),
                        style = MaterialTheme.typography.subtitle1,
                        color = Color(80,80,80)
                    )
                }
                Text(
                    text = SimpleDateFormat("hh:mm:ss").format(date),
                    modifier = Modifier.padding(4.dp).align(Alignment.End),
                    color = Color(150,150,150),
                    style = MaterialTheme.typography.body2
                )
            }
            if(arrangement == Arrangement.End){
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource("images/head.jpg"),
                    contentDescription = "your profile head image",
                    modifier = Modifier.padding(top = 16.dp)
                        .size(64.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }

}
enum class Type {
    SHOWN, HIDE
}

data class Chat(
    var user: User,
    var message: String,
    var type: Type,
    var date: Date
)