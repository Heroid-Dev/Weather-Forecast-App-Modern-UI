package com.example.weatherv1.widgets

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.weatherv1.R
import com.example.weatherv1.ui.theme.ColorSurface
import com.example.weatherv1.utils.customShadow


@Composable
fun DailyForecast(
    modifier: Modifier = Modifier,
    // weatherInfo: Weather
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth(.67f)
            .fillMaxHeight(.47f)//45f
    ) {
        val (
            forecastImage,
            forecastValue,
            description,
            background
        ) = createRefs()

        CardBackground(
            modifier = Modifier.constrainAs(background) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = parent.top,
                    bottom = parent.bottom,
                    topMargin = 25.dp,//25.dp
                )
                height = Dimension.fillToConstraints
            }
        )

        Image(
            painter = painterResource(id = R.drawable.img_sub_rain),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(174.dp)//174.dp
                .constrainAs(forecastImage) {
                    start.linkTo(anchor = parent.start)
                    top.linkTo(anchor = parent.top)
                    end.linkTo(anchor = parent.end, margin = 15.dp)
                }
        )

        ForecastValue(modifier = Modifier.constrainAs(forecastValue) {
            top.linkTo(anchor = forecastImage.bottom)
            bottom.linkTo(anchor = description.top)
            start.linkTo(anchor = parent.start, margin = 10.dp)
            end.linkTo(anchor = parent.end)
        })

        ForecastDescription(modifier = Modifier.constrainAs(description) {
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = parent.end)
            top.linkTo(anchor = forecastValue.bottom, margin = 5.dp)
        })
    }
}

@Composable
fun ForecastValue(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "26°",
            fontSize = 80.sp,
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
fun ForecastDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Rain Showers",
            color = ColorSurface,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Monday,12 Feb",
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
                        Color(0x977F82FF), // شفاف‌تر شده
                        Color(0xA85BBCFF),
                        Color(0xD8C5E5FF),
                        //Color(0xCC7F82FF), // شفاف‌تر شده
                        //Color(0xBB5BBCFF),
                        //Color(0xFFC5E5FF),
                    )
                )
            )
            .blur(66.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun DailyForecastPreview() {
    DailyForecast(
        //weatherInfo = weatherInfo
    )
}