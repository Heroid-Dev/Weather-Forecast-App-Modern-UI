package com.example.weatherv1.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.utils.customShadow


@Composable
fun HourlyForecast() {

    val hourlyForecast = listOf(
        HourlyWeather("15:00", 26.0, R.drawable.sunny_hourly, 75.0),
        HourlyWeather("16:00", 25.0, R.drawable.rain_hourly, 40.0),
        HourlyWeather("17:00", 24.0, R.drawable.sunny_hourly, 40.0),
        HourlyWeather("18:00", 23.0, R.drawable.half_cloudy_hourly, 40.0),
        HourlyWeather("19:00", 22.0, R.drawable.cloud_hourly, 10.0)
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(
            items = hourlyForecast,

        ) {hourlyWeather->
            HourlyForecastItem(hourlyWeather)
        }
    }
}

@Composable
fun HourlyForecastItem(hourlyWeather: HourlyWeather) {
    Box(
        modifier = Modifier.padding(horizontal = 5.dp).width(80.dp).height(120.dp)
            .customShadow(
                color = Color(0xFF5EB6FF),
                alpha = .8f,
                borderRadius = 15.dp,
                shadowRadius = 10.dp,
                offsetY =5.dp,
            )
            .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0x727F82FF),
                    Color(0x435BBCFF),
                    Color(0xBFC5E5FF)
                )
            ),
            shape = RoundedCornerShape(15.dp)
        ),
    ) {
        Column(modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround) {
            Text(text=hourlyWeather.hour, color = Color.White, fontWeight = FontWeight.Medium)
            Image(painter = painterResource(hourlyWeather.iconRes), contentDescription = null,
                modifier = Modifier.size(40.dp))
            Text(text="${hourlyWeather.temperature.toInt()}°",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        .3f to Color.White,
                        .7f to Color.White.copy(.3f)
                    ),
                    shadow = Shadow(color = Color.Gray),
                    fontWeight = FontWeight.Bold
                )
                )
        }
    }
}

data class HourlyWeather(
    val hour: String,
    val temperature: Double,
    @DrawableRes val iconRes: Int,
    val precipProb: Double
)

@Preview(backgroundColor = 0xFF91F1FF, showBackground = true)
@Composable
private fun HourlyForecastPreview() {
    HourlyForecast()
}