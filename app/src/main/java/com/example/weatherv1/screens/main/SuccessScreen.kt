package com.example.weatherv1.screens.main

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.widgets.AirQualityBox
import com.example.weatherv1.widgets.AirQualityBox2
import com.example.weatherv1.widgets.AirQualityBox3
import com.example.weatherv1.widgets.AppBarSuccessScreen
import com.example.weatherv1.widgets.DailyForecast
import com.example.weatherv1.widgets.HourlyForecast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    //weatherInfo: Weather
) {
    val infiniteTransition = rememberInfiniteTransition()
    val color1 = infiniteTransition.animateColor(
        initialValue = Color(0xFFEDFAFF),
        targetValue = Color(0xFF9FD6FF),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorBackground1"
    ).value
    val color2 = infiniteTransition.animateColor(
        initialValue = Color(0xFF5BBCFF),
        targetValue = Color(0xFFEFF9FF),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorBackground2"
    ).value

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppBarSuccessScreen(
                        //weatherInfo = weatherInfo
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DailyForecast(
                        //weatherInfo = weatherInfo
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    AirQualityBox3(
                        modifier = Modifier.padding(horizontal = 40.dp)
                        //weatherInfo = weatherInfo
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SuccessScreenPreview() {
    SuccessScreen()
}