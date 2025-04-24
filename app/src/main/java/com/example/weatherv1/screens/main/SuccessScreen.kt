package com.example.weatherv1.screens.main

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.utils.customShadow
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
                    .fillMaxSize()
            ) {
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
                HeaderHourlyForecast{

                }
                HourlyForecast()
            }
        }
    }
}

@Composable
fun HeaderHourlyForecast(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .fillMaxWidth()

            .background(color = Color.White.copy(alpha = .2f), shape = RoundedCornerShape(10.dp))
            .customShadow(
                color = Color(0xFF2B8DE5),
                alpha = .2f,
                borderRadius = 10.dp,
                shadowRadius = 12.dp,
                offsetY = 7.dp
            )
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Today",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF234195)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Text(
                text = "7-Day Forecasts", style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF3156B2)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.arrow_right),
                tint = Color(0xFF3156B2),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SuccessScreenPreview() {
    SuccessScreen()
}