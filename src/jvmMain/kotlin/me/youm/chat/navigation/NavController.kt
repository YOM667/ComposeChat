package me.youm.chat.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable


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