package me.youm.chat.utils

import kotlin.math.absoluteValue

inline val Double.second: Int get() = (absoluteValue * 1000).toInt()

inline val Double.minute: Int get() = (absoluteValue * 1000 * 60).toInt()