package com.example.weatherv1.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.model.Hour
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.utils.customShadow
import com.example.weatherv1.utils.getWeatherIconFromCondition


@Composable
fun HourlyForecast(
    modifier: Modifier= Modifier,
    listOfHour: List<Hour>,
    mainViewModel: MainViewModel,
) {

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(
            items = listOfHour,

            ) { hourlyWeather ->
            HourlyForecastItem(hourlyWeather = hourlyWeather, mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun HourlyForecastItem(hourlyWeather: Hour, mainViewModel: MainViewModel) {
    val unitPref = mainViewModel.unitPrefs.collectAsState().value
    Box(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .width(90.dp)
            .height(115.dp)
            .customShadow(
                color = Color(0xFF5EB6FF),
                alpha = .8f,
                borderRadius = 15.dp,
                shadowRadius = 10.dp,
                offsetY = 5.dp,
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
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 3.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Text(
                text = hourlyWeather.datetime.removeSuffix(":00"),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
            Image(
                painter = painterResource(getWeatherIconFromCondition(hourlyWeather.icon)),
                contentDescription = null,
                modifier = Modifier.size(38.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Row {
                    Icon(
                        painter = painterResource(R.drawable.temp),
                        contentDescription = "temp icon",
                        modifier = Modifier.size(15.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = if (unitPref.isFahrenheit)
                            "${celsiusToFahrenheit(hourlyWeather.temp).toInt()}F"
                        else
                            "${hourlyWeather.temp.toInt()}°",
                        style = TextStyle(
                            brush = Brush.linearGradient(
                                .3f to Color.White,
                                .7f to Color.White.copy(.3f)
                            ),
                            shadow = Shadow(color = Color.Gray),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize
                        )
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(R.drawable.precip),
                        contentDescription = "Rain icon",
                        modifier = Modifier.size(15.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = if (unitPref.inRain_mm) "${hourlyWeather.precip.toInt()}mm" else "${hourlyWeather.precipprob.toInt()}%",
                        style = TextStyle(
                            brush = Brush.linearGradient(
                                .3f to Color.White,
                                .7f to Color.White.copy(.3f)
                            ),
                            shadow = Shadow(color = Color.Gray),
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize
                        )
                    )
                }
            }

        }
    }
}

