package com.example.weatherv1.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@Composable
fun WeatherCard(
    modifier: Modifier= Modifier,
    onCardClicked:()-> Unit,
    @DrawableRes weatherImage: Int= R.drawable.img_sun,
    time: String="",
    maxTemp: String="",
    minTemp: String="",
    status: String="",
    humidity: Int=1,
    Precipitation: String="",
    Wind: String=""
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
                .background(color = Color(0xC6FFFFFF), shape = RoundedCornerShape(30.dp))
                .constrainAs(background) {
                    linkTo(
                        start = parent.start,
                        top = parent.top,
                        end = parent.end,
                        bottom = parent.bottom,
                        topMargin = 50.dp
                    )
                }
                .clickable{
                    onCardClicked()
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
                fontWeight = FontWeight.Medium,
                color = Color(0xFF204B6E)
            )
            Text(
                text = "${maxTemp}/${minTemp}",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF204B6E)
            )
            Text(
                text = status,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W300,
                color = Color(0xFF204B6E)
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
                Text(text = "Humidity",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF204B6E))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.precip),
                    contentDescription = "Rain icon"
                )
                Text(text = Precipitation)
                Text(text = "Rain",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF204B6E))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.wind),
                    contentDescription = null
                )
                Text(text = Wind)
                Text(text = "Wind speed",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF204B6E))
            }
        }
    }

}

