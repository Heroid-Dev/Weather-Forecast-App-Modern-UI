package com.example.weatherv1.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.model.Hour
import com.example.weatherv1.utils.customShadow
import com.example.weatherv1.utils.getWeatherIconFromCondition


@Composable
fun HourlyForecast(listOfHour: List<Hour>) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(
            items = listOfHour,

        ) {hourlyWeather->
            HourlyForecastItem(hourlyWeather)
        }
    }
}

@Composable
fun HourlyForecastItem(hourlyWeather: Hour) {
    Box(
        modifier = Modifier.padding(horizontal = 5.dp).width(80.dp).height(110.dp)
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
            Text(text=hourlyWeather.datetime.removeSuffix(":00"), color = Color.White, fontWeight = FontWeight.Medium)
            Image(painter = painterResource(getWeatherIconFromCondition(hourlyWeather.icon)), contentDescription = null,
                modifier = Modifier.size(40.dp))
            Text(text="${hourlyWeather.temp.toInt()}°",
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

