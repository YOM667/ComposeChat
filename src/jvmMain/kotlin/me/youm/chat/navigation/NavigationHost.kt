package me.youm.chat.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable

/**
 * NavigationHost class
 */
class NavigationHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {

    @Composable
    fun build() {
        NavigationGraphBuilder().renderContents()
    }

    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController
    ) {
        @Composable
        fun renderContents() {
            this@NavigationHost.contents(this)
        }
    }
}


/**
 * Composable to build the Navigation Host
 */
@Composable
fun NavigationHost.NavigationGraphBuilder.composable(
    route: String,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = navController.currentScreen.value == route,
        enter = slideInVertically{fullHeight -> -fullHeight },
        exit = slideOutVertically { fullHeight -> -fullHeight }
    ){
        content()
    }
}