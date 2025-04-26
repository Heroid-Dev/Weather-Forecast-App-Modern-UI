package com.example.weatherv1.screens.nextday

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.utils.customShadow
import com.example.weatherv1.widgets.InfiniteColorBackground


data class InfoDay(
    val day: String,
    @DrawableRes val icon: Int,
    val conditions: String,
    val maxTemp: Int,
    val minTemp: Int
)


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
    InfiniteColorBackground(
        color1 = Color(0xFF38B0FF),
        color2 = Color(0xFF96C0EA)
    ) { color1,_->
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

                Text(
                    text = "${infoDay.maxTemp}°/${infoDay.minTemp}°",
                    fontWeight = FontWeight.Black,
                    color = Color.Gray
                )

            }
        }
    }

}
