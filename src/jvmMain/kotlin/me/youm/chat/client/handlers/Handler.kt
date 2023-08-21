package me.youm.chat.client.handlers

import me.youm.chat.ComposeChat
import me.youm.chat.client.Client



typealias Handler = (String) -> Unit
object HandlerFactory{
    fun create() {
        ComposeChat.client.addMessageHandler("message"){
        }
        ComposeChat.client.addMessageHandler("heartbeat"){
        }
    }
}



fun Client.addMessageHandler(name: String, handler: Handler){
    handlers[name] = handler
}