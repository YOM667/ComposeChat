package me.youm.chat.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.youm.chat.ComposeChat
import me.youm.chat.theme.LightPurpleColor
import me.youm.chat.utils.second

val transitionTime = 1.0.second
@Composable
fun HomeScreen(globalShow:MutableState<Boolean>){
    var show by remember { mutableStateOf(false) }
    var move by remember { mutableStateOf(false) }
    var wait by remember { mutableStateOf(true) }
    val constraintsScope = rememberCoroutineScope()
    remember {
        constraintsScope.launch {
            if(ComposeChat.client.socketState){
                move = true
                delay(transitionTime.toLong())
                show = true
                delay(0.1.second.toLong())
                globalShow.value = true
            }else{
                while (!wait){
                    wait = ComposeChat.client.socketState
                }
            }
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
                this.translationY = transition.value
            }.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Rounded.Send,
                contentDescription = null,
                modifier = Modifier.background(Color.White),
                tint = LightPurpleColor.primary
            )
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = show,
            enter = fadeIn(), exit = fadeOut()
        ) {
            Text(
                text = "Compose Chat",
                color = LightPurpleColor.primary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }

    }
}