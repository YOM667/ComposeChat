package me.youm.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

import me.youm.chat.theme.LightPurpleColor


@Composable
fun HeadImage(user: User){
    if (!user.canDisplayHeadImage()) {
        Image(
            imageVector = Icons.Rounded.AccountBox,
            contentDescription = "your profile head image",
            modifier = Modifier.padding(top = 16.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp)),
            colorFilter = ColorFilter.tint(LightPurpleColor.primary)
        )
    }else{
        Image(
            imageVector = Icons.Rounded.AccountCircle,
            contentDescription = "your profile head image",
            modifier = Modifier.padding(top = 16.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}
data class User(
    var nickName:String,
    var sex: Sex,
    var headImage: String?,
    var account: Account
)

fun User.canDisplayHeadImage(): Boolean = this.account != Account.Visitor && (this.headImage != null)

enum class Sex{
    MALE,FEMALE
}
enum class Account{
    Visitor,User,Admin,Owner
}