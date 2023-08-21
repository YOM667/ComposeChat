package me.youm.chat

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import me.youm.chat.client.Client
import me.youm.chat.components.Account
import me.youm.chat.components.Sex
import me.youm.chat.components.User

object ComposeChat {
    var client = Client("127.0.0.1", 1145)
    var user = User("游客${(1..999).random()}", Sex.FEMALE,null,Account.Visitor)
    val gson: Gson = GsonBuilder().create()
}