package com.example.weatherv1.screens.main

import android.icu.text.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.weatherv1.model.Hour
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.ui.theme.ColorSurface
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.utils.customShadow
import com.example.weatherv1.utils.getWeatherIconFromCondition
import java.util.Date


@Composable
fun DailyForecast(
    modifier: Modifier = Modifier,
    nowWeather: Hour,
    mainViewModel: MainViewModel,
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth(.67f)
            .fillMaxHeight(.46f)//45f
    ) {
        val (
            forecastImage,
            forecastValue,
            description,
            background,
        ) = createRefs()

        CardBackground(
            modifier = Modifier.constrainAs(background) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = parent.top,
                    bottom = parent.bottom,
                    topMargin = 25.dp,
                )
                height = Dimension.fillToConstraints
            }
        )
        Image(
            painter = painterResource(id = getWeatherIconFromCondition(condition = nowWeather.icon)),
            contentDescription = "forecast card icon",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(174.dp)
                .constrainAs(forecastImage) {
                    start.linkTo(anchor = parent.start)
                    top.linkTo(anchor = parent.top)
                    end.linkTo(anchor = parent.end)
                }
        )

        ForecastValue(
            temp = if (mainViewModel.unitPrefs.collectAsState().value.isFahrenheit)
                "%.1f".format(celsiusToFahrenheit(nowWeather.temp)) + "F"
            else
                "${nowWeather.temp.toInt()}°",
            modifier = Modifier.constrainAs(forecastValue) {
                top.linkTo(anchor = forecastImage.bottom)
                bottom.linkTo(anchor = description.top)
                start.linkTo(anchor = parent.start, margin = 15.dp)
                end.linkTo(anchor = parent.end)
            })

        ForecastDescription(
            conditions = nowWeather.conditions,
            datetimeEpoch = nowWeather.datetimeEpoch.toLong(),
            modifier = Modifier.constrainAs(description) {
                start.linkTo(anchor = parent.start)
                end.linkTo(anchor = parent.end)
                top.linkTo(anchor = forecastValue.bottom)
                bottom.linkTo(anchor = parent.bottom, margin = 20.dp)
            })
    }
}

@Composable
fun ForecastValue(modifier: Modifier, temp: String) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = temp,
            fontSize = 70.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    .3f to Color.White,
                    .7f to Color.White.copy(.3f)
                )
            ),
            fontWeight = FontWeight.Black
        )
    }
}


@Composable
fun ForecastDescription(
    modifier: Modifier = Modifier,
    conditions: String,
    datetimeEpoch: Long,
) {
    val dateFormat = DateFormat.getDateInstance(DateFormat.ERA_FIELD)
    val formatted = dateFormat.format(Date(datetimeEpoch * 1000))
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = conditions,
            color = ColorSurface,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = formatted,
            color = ColorSurface,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun CardBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .customShadow(
                color = Color(0xFF2B8DE5),
                alpha = .8f,
                borderRadius = 25.dp,
                shadowRadius = 28.dp,
                offsetY = 24.dp,
                offsetX = 1.dp
            )
            .clip(RoundedCornerShape(32.dp))
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0x977F82FF),
                        Color(0xA85BBCFF),
                        Color(0xD8C5E5FF)
                    )
                )
            )
            .blur(66.dp)
    )
}

