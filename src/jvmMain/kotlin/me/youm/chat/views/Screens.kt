package me.youm.chat.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Screens
 */
enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Home",
        icon = Icons.Filled.Home
    ),
    ChatRoomScreen(
        label = "Chat Room",
        icon = Icons.Filled.Email
    ),
    SettingsScreen(
        label = "Settings",
        icon = Icons.Filled.Settings
    ),
}