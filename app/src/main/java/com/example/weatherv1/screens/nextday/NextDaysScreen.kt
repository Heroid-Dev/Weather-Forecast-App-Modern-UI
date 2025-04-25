package com.example.weatherv1.screens.nextday

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherv1.R
import com.example.weatherv1.utils.customShadow
import com.example.weatherv1.widgets.TopAppBarButton

@Composable
fun NextDaysScreen() {
    val transition = rememberInfiniteTransition()
    val color1 = transition.animateColor(
        initialValue = Color(0xFF5BBCFF),
        targetValue = Color(0xFF686CFF),
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    val color2 = transition.animateColor(
        initialValue = Color(0xFF686CFF),
        targetValue = Color(0xFF5BBCFF),
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .background(
                    Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopAppBarButton(
                    modifier = Modifier.size(70.dp),
                    icon = R.drawable.left_arrow,
                    iconSize = 30.dp,
                    iconDescription = "arrow_back",
                    onClickButton = {}
                )
                Text(
                    modifier = Modifier.padding(start = 35.dp),
                    text = "Next 7 Days Forecast",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth()
        ) {
            TomorrowCard()
            Text(
                modifier= Modifier.padding(start = 8.dp, top = 50.dp, bottom = 5.dp),
                text="EveryDays",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold)
            RowsOfDaysOfTheWeek()
        }
    }
}

@Composable
fun RowsOfDaysOfTheWeek() {
    val forecastList = listOf(
        InfoDay(
            day = "Saturday",
            icon = R.drawable.img_sun,
            conditions = "Sunny",
            maxTemp = 30,
            minTemp = 18
        ),
        InfoDay(
            day = "Sunday",
            icon = R.drawable.img_sub_rain,
            conditions = "Partly Cloudy",
            maxTemp = 27,
            minTemp = 17
        ),
        InfoDay(
            day = "Monday",
            icon = R.drawable.img_rain,
            conditions = "Rainy",
            maxTemp = 22,
            minTemp = 15
        ),
        InfoDay(
            day = "Tuesday",
            icon = R.drawable.img_thunder,
            conditions = "Thunderstorm",
            maxTemp = 24,
            minTemp = 16
        ),
        InfoDay(
            day = "Wednesday",
            icon = R.drawable.img_snow,
            conditions = "Snowy",
            maxTemp = -2,
            minTemp = -10
        )
    )
    LazyColumn {
        items(
            items = forecastList,
            key = {
                it.day
            }
        ) { infoDay ->
            RowsOfDaysOfTheWeekItem(infoDay)
        }
    }
}

@Composable
fun RowsOfDaysOfTheWeekItem(
    infoDay: InfoDay
) {
    val transition=rememberInfiniteTransition()
    val color1 = transition.animateColor(
        initialValue =  Color(0xFF38B0FF),
        targetValue = Color(0xFF96C0EA),
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    ).value
//    val colorFloat= transition.animateFloat(
//        initialValue = 1f,
//        targetValue = .1f,
//        animationSpec = InfiniteRepeatableSpec(
//            animation = tween(durationMillis = 3000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    ).value
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(80.dp)
            .customShadow(
                color = color1,
                alpha = .6f,
                borderRadius = 12.dp,
                shadowRadius = 8.dp,
                offsetY = 2.dp
            )
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFFFFFFF),
                        Color(0xFFD6EFFF),
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1.5f),
                text = infoDay.day,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Image(
                painter = painterResource(infoDay.icon),
                contentDescription = infoDay.conditions,
                modifier = Modifier
                    .height(40.dp)
                    .padding(end = 10.dp)
                    .weight(1f),
                contentScale = ContentScale.FillHeight
            )


            Text(
                text = infoDay.conditions,
                modifier = Modifier.weight(2f),
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Text(text = "${infoDay.maxTemp}°/${infoDay.minTemp}°",
                fontWeight = FontWeight.Black,
                color = Color.Gray)
        }
    }
}

@Composable
private fun TomorrowCard() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(top = 100.dp)
    ) {
        val (
            forecastImage,
            forecastValue,
            airQuality,
            background
        ) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .customShadow(
                    color = Color(0xFFA8D8FA),
                    alpha = .6f,
                    borderRadius = 50.dp,
                    shadowRadius = 20.dp,
                    offsetY = 20.dp
                )
                .background(color = Color.White, shape = RoundedCornerShape(24.dp))
                .constrainAs(background) {
                    linkTo(
                        start = parent.start,
                        top = parent.top,
                        end = parent.end,
                        bottom = parent.bottom,
                        topMargin = 50.dp
                    )
                }
        )

        Image(
            painter = painterResource(id = R.drawable.img_rain),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(164.dp)
                .constrainAs(forecastImage) {
                    start.linkTo(anchor = parent.start)
                    top.linkTo(anchor = parent.top, margin = 10.dp)

                }
        )
        Column(modifier = Modifier.constrainAs(forecastValue) {
            start.linkTo(anchor = forecastImage.end)
            top.linkTo(anchor = background.top, margin = 40.dp)
            end.linkTo(anchor = background.end)
        }) {
            Text(
                text = "Tomorrow",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "23°/17°",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Rainy",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Light
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .constrainAs(airQuality) {
                    top.linkTo(anchor = forecastValue.bottom)
                    start.linkTo(anchor = forecastImage.start)
                    bottom.linkTo(anchor = background.bottom)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = "humidity icon"
                )
                Text(text = "30%")
                Text(text = "Humidity")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.precip),
                    contentDescription = "Precipitation icon"
                )
                Text(text = "30%")
                Text(text = "Precipitation")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.wind),
                    contentDescription = null
                )
                Text(text = "30%")
                Text(text = "Wind speed")
            }
        }
    }

}

data class InfoDay(
    val day: String,
    @DrawableRes val icon: Int,
    val conditions: String,
    val maxTemp: Int,
    val minTemp: Int
)


@Preview(showBackground = true)
@Composable
private fun NextDaysScreenPreview() {
    NextDaysScreen()
}