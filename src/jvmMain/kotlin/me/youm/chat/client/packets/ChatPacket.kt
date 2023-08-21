package me.youm.chat.client.packets

import me.youm.chat.components.User
import java.util.*

data class ChatPacket(
    var message:String,
    var sender: User,
    var date: Date
)
