package com.example.weatherv1.widgets

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun InfiniteColorBackground(
    color1: Color,
    color2: Color,
    color3: Color= Color.Transparent,
    color4: Color= Color.Transparent,
     animateColor:@Composable (Color, Color)-> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val infiniteColor1 = infiniteTransition.animateColor(
        initialValue = color1,
        targetValue = color2,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorBackground1"
    ).value
    val infiniteColor2 = infiniteTransition.animateColor(
        initialValue = color3,
        targetValue = color4,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorBackground2"
    ).value
    animateColor(infiniteColor1,infiniteColor2)
}