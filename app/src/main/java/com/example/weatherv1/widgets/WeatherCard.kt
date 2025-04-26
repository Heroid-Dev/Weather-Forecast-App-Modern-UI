package com.example.weatherv1.widgets

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherv1.R
import com.example.weatherv1.utils.customShadow

@Composable
fun WeatherCard(
    modifier: Modifier= Modifier,
    @DrawableRes weatherImage: Int=R.drawable.img_rain,
    time: String="Tomorrow",
    maxTemp:Int=23,
    minTemp: Int=17,
    status: String="Rainy",
    humidity: Int=30,
    Precipitation: Int=30,
    Wind: Int=30
) {
    ConstraintLayout(
        modifier = modifier
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
            painter = painterResource(id = weatherImage),
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
                text = time,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${maxTemp}°/${minTemp}°",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Black
            )
            Text(
                text = status,
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
                Text(text = "$humidity%")
                Text(text = "Humidity")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.precip),
                    contentDescription = "Precipitation icon"
                )
                Text(text = "$Precipitation%")
                Text(text = "Precipitation")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.wind),
                    contentDescription = null
                )
                Text(text = "${Wind}Km/h")
                Text(text = "Wind speed")
            }
        }
    }

}
