package me.youm.chat.components

data class User(
    var nickName:String,
    var sex: Sex,
)

enum class Sex{
    MALE,FEMALE
}