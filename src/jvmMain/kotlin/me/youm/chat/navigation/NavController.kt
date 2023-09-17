package me.youm.chat.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.youm.chat.ComposeChat
import me.youm.chat.components.Chat
import me.youm.chat.theme.LightPurpleColor
import me.youm.chat.views.*


/**
 * copy from <div>https://github.com/itheamc/navigation-for-compose-for-desktop</div>
 *
 * NavController Class
 */
class NavController(
    private val startDestination: String,
    private var backStackScreens: MutableSet<String> = mutableSetOf()
) {
    // Variable to store the state of the current screen
    var currentScreen: MutableState<String> = mutableStateOf(startDestination)

    // Function to handle the navigation between the screen
    fun navigate(route: String) {
        if (route != currentScreen.value) {
            if (this.backStackScreens.contains(this.currentScreen.value) && this.currentScreen.value != startDestination) {
                this.backStackScreens.remove(this.currentScreen.value)
            }

            if (route == startDestination) {
                this.backStackScreens = mutableSetOf()
            } else {
                this.backStackScreens.add(this.currentScreen.value)
            }

            this.currentScreen.value = route
        }
    }

    // Function to handle the back
    fun navigateBack() {
        if (this.backStackScreens.isNotEmpty()) {
            this.currentScreen.value = this.backStackScreens.last()
            this.backStackScreens.remove(this.currentScreen.value)
        }
    }
}


/**
 * Composable to remember the state of the navcontroller
 */
@Composable
fun rememberNavController(
    startDestination: String,
    backStackScreens: MutableSet<String> = mutableSetOf()
): MutableState<NavController> = rememberSaveable {
    mutableStateOf(NavController(startDestination, backStackScreens))
}


@Composable
fun CustomNavigationHost(
    navController: NavController,
    route: @Composable (NavigationHost.NavigationGraphBuilder) -> Unit,
) {
    NavigationHost(navController) {
        route(this)
    }.build()
}

/**
 * navigation router to show and update current screen
 */
@Composable
fun Router(
    globalShow:MutableState<Boolean>,
    chats: SnapshotStateList<Chat>
){
    val navController by rememberNavController(Screen.HomeScreen.label)
    var currentScreen by remember { navController.currentScreen }
    CoroutineScope(Dispatchers.IO).launch {
        ComposeChat.client.reader(chats::add)
    }
    CustomNavigationHost(navController){navController ->
        navController.composable(Screen.HomeScreen.label){
            HomeScreen(globalShow)
        }
        navController.composable(Screen.AccountSettingScreen.label){
            AccountSettingScreen()
        }
        navController.composable(Screen.ChatRoomScreen.label){
            ChatScreen(chats)
        }
    }
    TopBar(globalShow) { currentScreen = it }
}
@Composable
fun TopBar(globalShow: MutableState<Boolean>,setCurrentScreen: (String) -> Unit){
    Box(
        modifier = Modifier.shadow(8.dp)
            .background(LightPurpleColor.primary)
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .height(80.dp),
    ) {
        AnimatedVisibility(
            modifier = Modifier.padding(start = 48.dp).align(Alignment.CenterStart),
            visible = globalShow.value,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            Row{
                Box (
                    modifier = Modifier.clip(CircleShape)
                        .clickable { setCurrentScreen(Screen.HomeScreen.label) },
                ) {
                    Image(
                        imageVector = Screen.HomeScreen.icon,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp).size(48.dp),
                        colorFilter = ColorFilter.tint(Color.White),
                    )
                }
                Box (
                    modifier = Modifier.padding(start = 32.dp)
                        .clip(CircleShape)
                        .clickable { setCurrentScreen(Screen.ChatRoomScreen.label)  },
                ) {
                    Image(
                        imageVector = Screen.ChatRoomScreen.icon,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp).size(48.dp),
                        colorFilter = ColorFilter.tint(Color.White),
                    )
                }
            }
        }
        AnimatedVisibility(
            modifier = Modifier.padding(end = 64.dp).align(Alignment.CenterEnd),
            visible = globalShow.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box (
                modifier = Modifier.clip(CircleShape)
                    .clickable { setCurrentScreen(Screen.AccountSettingScreen.label) },
            ) {
                Image(
                    imageVector = Screen.AccountSettingScreen.icon,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).size(48.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                )
            }
        }
    }
}