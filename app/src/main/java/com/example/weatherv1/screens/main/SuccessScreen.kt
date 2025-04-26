package com.example.weatherv1.screens.main

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.widgets.InfiniteColorBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    //weatherInfo: Weather
) {
    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2=Color(0xFF9FD6FF),
        color3=Color(0xFF5BBCFF),
        color4=Color(0xFFEFF9FF)
    ) { color1,color2->
        Scaffold { innerPadding ->
            Column(
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
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppBarSuccessScreen(
                        //weatherInfo = weatherInfo
                    )
                    DailyForecast(
                        //weatherInfo = weatherInfo
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier
                            .background(
                                color = Color(0x28FFFFFF), shape =
                                    RoundedCornerShape(10.dp)
                            )
                            .padding(5.dp)
                            .basicMarquee(
                                iterations = 1000,
                                repeatDelayMillis = 1000,

                                ),
                        text = "Similar temperatures continuing with a chance of rain tomorrow & Wednesday.",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF234195)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    AirQualityBox(
                        modifier = Modifier.padding(horizontal = 40.dp)
                        //weatherInfo = weatherInfo
                    )
                    HeaderHourlyForecast(
                        modifier = Modifier.padding(top = 8.dp),
                        onClick = {
                            //
                        }
                    )
                }
                HourlyForecast()
            }
        }
    }
}



@Preview
@Composable
private fun SuccessScreenPreview() {
    SuccessScreen()
}