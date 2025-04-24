package com.example.weatherv1.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherv1.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(navigationToMainScreen: () -> Unit) {

    val scale = remember { Animatable(0f) }
    var textShow by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
        delay(1000)
        textShow = true
        delay(4000)
        withContext(Dispatchers.Main) {
            navigationToMainScreen()
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "GradientShift")

    val color1 by infiniteTransition.animateColor(
        initialValue = Color(0xFF0CBDFF),
        targetValue = Color(0xFF2D7BDE),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Color1Animation"
    )

    val color2 by infiniteTransition.animateColor(
        initialValue = Color(0xFF0051BA),
        targetValue = Color(0xFF3CC9FF),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Color2Animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(color1,color2),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale.value,
                        scaleY = scale.value
                    )
                    .size(280.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.weathericon2),
                contentDescription = stringResource(R.string.weather_icon),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(
                visible = textShow,
                enter = slideInVertically(
                    initialOffsetY = {
                        it / 2
                    }
                ) + fadeIn(animationSpec = tween(300))
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Weather",
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFC400)
                    )
                    Text(
                        text = "Forecast App.",
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen { }
}