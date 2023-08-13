package me.youm.chat.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.youm.chat.theme.LightPurpleColorPalette
import me.youm.chat.utils.second

var globalShow: Boolean = false
val transitionTime = 1.0.second
val waitTime = 2.0.second
@Composable
fun HomeScreen(){

    var show by remember { mutableStateOf(false) }
    var move by remember { mutableStateOf(false) }
    val constraintsScope = rememberCoroutineScope()

    remember {
        constraintsScope.launch {
            delay(waitTime.toLong())
            move = true
            delay((transitionTime * 0.2).toLong())
            show = true
            delay(transitionTime.toLong()) // wait transition animation is finished
            globalShow = true
        }
    }
    val transition = animateFloatAsState(
        targetValue = if (move) -116f else 0f,
        animationSpec = tween(durationMillis = transitionTime, easing = LinearOutSlowInEasing)
    )
    Box(
        modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(1f)
            .padding(top = 80.dp)
    ) {
        Surface(
            modifier = Modifier.size(92.dp).graphicsLayer {
                this.translationY = if(globalShow) -116f else transition.value
            }.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Rounded.Send,
                contentDescription = null,
                tint = LightPurpleColorPalette.primary
            )
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = if(globalShow) true else show,
            enter = fadeIn(), exit = fadeOut()
        ) {
            Text(
                text = "Compose Chat",
                color = LightPurpleColorPalette.primary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }

    }
}