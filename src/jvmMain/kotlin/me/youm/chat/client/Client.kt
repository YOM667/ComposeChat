package me.youm.chat.client

import kotlinx.coroutines.*
import me.youm.chat.ComposeChat
import me.youm.chat.client.handlers.Handler
import me.youm.chat.client.handlers.HandlerFactory
import me.youm.chat.client.packets.ChatPacket
import me.youm.chat.client.packets.Packets
import me.youm.chat.components.Chat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*


class Client(host: String, port: Int) {
    lateinit var socket: Socket
    lateinit var writer : PrintWriter
    lateinit var reader : BufferedReader
    var handlers: MutableMap<String, Handler> = mutableMapOf()
    var socketState: Boolean = false

    init {
        try {
            socket = Socket(host, port)
            writer = PrintWriter(socket.getOutputStream(), true)
            reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            socketState = socket.isConnected
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun start() {
        HandlerFactory.create()
        while (socketState) {
            socketState = socket.isConnected
        }
    }

    fun sendMessage(message: String, type: Packets) {
        val toJson = ComposeChat.gson.toJson(ChatPacket(sender = ComposeChat.user, message = message, date = Date()))
        writer.println(toJson)
    }

    suspend inline fun reader(crossinline addChat: (Chat) -> Unit) = coroutineScope {
        launch {
            while (socketState) {
                val chat = ComposeChat.gson.fromJson(reader.readLine(), ChatPacket::class.java)
                addChat(Chat(user = chat.sender, message = chat.message, date = chat.date))
            }
        }
    }


}
